package com.dave_devs.stockmarketapp.feature_stock.data.repository

import com.dave_devs.stockmarketapp.feature_stock.data.csv.CSVParser
import com.dave_devs.stockmarketapp.feature_stock.data.local.StockDao
import com.dave_devs.stockmarketapp.feature_stock.data.mapper.toCompanyListing
import com.dave_devs.stockmarketapp.feature_stock.data.mapper.toCompanyListingEntity
import com.dave_devs.stockmarketapp.feature_stock.data.remote.StockApi
import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyListing
import com.dave_devs.stockmarketapp.feature_stock.domain.repository.StockRepository
import com.dave_devs.stockmarketapp.feature_stock.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val dao: StockDao,
    private val companyListingsParser: CSVParser<CompanyListing>
): StockRepository {

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> = flow {

        emit(Resource.Loading(isLoading = true))

        val localListings = dao.searchCompanyListing(query)
        emit(Resource.Success(
            data = localListings.map{it.toCompanyListing()}
        ))

        val isDatabaseEmpty = localListings.isEmpty() && query.isBlank()
        val shouldJustFetchFromCache = !isDatabaseEmpty && !fetchFromRemote
        if (shouldJustFetchFromCache) {
            emit(Resource.Loading(isLoading = false))
            return@flow
        }

        val remoteListings = try{
            val response = api.getListings()
            companyListingsParser.parse(response.byteStream())
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(message = e.message?: "Couldn't load data"))
            null
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(message = e.message?: "Unknown error occurred"))
            null
        }

        remoteListings?.let{ listings ->
            dao.clearCompanyListings()
            dao.insertCompanyListings(
                listings.map{it.toCompanyListingEntity()}
            )
            emit(Resource.Success(data = dao.searchCompanyListing( query = "" ).map{
                it.toCompanyListing()
            }))
            emit(Resource.Loading(isLoading = false))
        }
    }
}