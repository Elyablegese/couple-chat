package com.iia.couplechat.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iia.couplechat.data.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CoupleChatDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
}