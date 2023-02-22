package com.dave_devs.stockmarketapp.feature_stock.data.mapper

import com.dave_devs.stockmarketapp.feature_stock.data.local.CompanyListingEntity
import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyListing

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}