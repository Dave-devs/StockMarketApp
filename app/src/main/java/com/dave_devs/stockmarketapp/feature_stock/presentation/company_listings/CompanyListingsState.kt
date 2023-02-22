package com.dave_devs.stockmarketapp.feature_stock.presentation.company_listings

import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = " "
)