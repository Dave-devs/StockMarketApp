package com.dave_devs.stockmarketapp.feature_stock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dave_devs.stockmarketapp.core.Constants.COMPANY_LISTING_TABLE

@Entity(tableName = COMPANY_LISTING_TABLE)
data class CompanyListingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val symbol: String,
    val name: String,
    val exchange: String
)
