package com.dave_devs.stockmarketapp.feature_stock.data.mapper

import com.dave_devs.stockmarketapp.feature_stock.data.local.CompanyListingEntity
import com.dave_devs.stockmarketapp.feature_stock.data.remote.dto.CompanyInfoDto
import com.dave_devs.stockmarketapp.feature_stock.domain.model.CompanyInfo
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

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}