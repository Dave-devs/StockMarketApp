package com.dave_devs.stockmarketapp.feature_stock.domain.model

import java.time.LocalDateTime

data class IntraDayInfo(
    val date: LocalDateTime,
    val close: Double
)