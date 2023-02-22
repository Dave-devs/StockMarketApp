package com.dave_devs.stockmarketapp.feature_stock.data.remote

import com.dave_devs.stockmarketapp.core.Constants.API_KEY
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query ("apikey") apiKey: String = API_KEY
    ): ResponseBody
}