package com.dave_devs.stockmarketapp.feature_stock.data.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parse(stream: InputStream): List<T>
}