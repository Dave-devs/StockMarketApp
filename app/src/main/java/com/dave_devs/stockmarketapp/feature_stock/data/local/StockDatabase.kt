package com.dave_devs.stockmarketapp.feature_stock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}