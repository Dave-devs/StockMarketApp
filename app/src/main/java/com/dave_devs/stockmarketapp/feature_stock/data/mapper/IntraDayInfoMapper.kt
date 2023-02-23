package com.dave_devs.stockmarketapp.feature_stock.data.mapper

import com.dave_devs.stockmarketapp.feature_stock.data.remote.dto.IntraDayInfoDto
import com.dave_devs.stockmarketapp.feature_stock.domain.model.IntraDayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntraDayInfoDto.toIntraDayInfo(): IntraDayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntraDayInfo(
        date = localDateTime,
        close = close
    )
}