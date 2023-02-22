package com.dave_devs.stockmarketapp.feature_stock.di

import android.app.Application
import androidx.room.Room
import com.dave_devs.stockmarketapp.core.Constants.BASE_URL
import com.dave_devs.stockmarketapp.core.Constants.STOCK_DATABASE
import com.dave_devs.stockmarketapp.feature_stock.data.local.StockDatabase
import com.dave_devs.stockmarketapp.feature_stock.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            STOCK_DATABASE
        ).build()
    }
}