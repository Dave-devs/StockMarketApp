package com.dave_devs.stockmarketapp.feature_stock.domain.repository

import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyInfo
import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyListing
import com.dave_devs.stockmarketapp.feature_stock.domain.model.IntraDayInfo
import com.dave_devs.stockmarketapp.feature_stock.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntraDayInfo(symbol: String): Resource<List<IntraDayInfo>>

    suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo>
}