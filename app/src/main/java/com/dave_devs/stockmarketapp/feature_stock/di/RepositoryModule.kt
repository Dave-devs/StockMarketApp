package com.dave_devs.stockmarketapp.feature_stock.di

import com.dave_devs.stockmarketapp.feature_stock.data.csv.CSVParser
import com.dave_devs.stockmarketapp.feature_stock.data.csv.CompanyListingParser
import com.dave_devs.stockmarketapp.feature_stock.data.repository.StockRepositoryImpl
import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyListing
import com.dave_devs.stockmarketapp.feature_stock.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

    @Singleton
    @Binds
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingParser
    ): CSVParser<CompanyListing>
}