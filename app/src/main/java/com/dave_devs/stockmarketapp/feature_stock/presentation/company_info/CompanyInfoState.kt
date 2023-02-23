package com.dave_devs.stockmarketapp.feature_stock.presentation.company_info

import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyInfo
import com.dave_devs.stockmarketapp.feature_stock.domain.model.IntraDayInfo

data class CompanyInfoState(
    val stockInfo: List<IntraDayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
