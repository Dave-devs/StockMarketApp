package com.dave_devs.stockmarketapp.feature_stock.data.csv

import com.dave_devs.stockmarketapp.feature_stock.data.mapper.toIntraDayInfo
import com.dave_devs.stockmarketapp.feature_stock.data.remote.dto.IntraDayInfoDto
import com.dave_devs.stockmarketapp.feature_stock.domain.model.IntraDayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntraDayInfoParser @Inject constructor(): CSVParser<IntraDayInfo> {

    override suspend fun parse(stream: InputStream): List<IntraDayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(index = 0) ?: return@mapNotNull null
                    val close = line.getOrNull(index = 4) ?: return@mapNotNull null
                    val dto = IntraDayInfoDto(timestamp, close.toDouble())
                    dto.toIntraDayInfo()
                }
                //There data from te current day that differs so we filter the date for the last day(24hrs)
                .filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also { csvReader.close() }
        }
    }
}