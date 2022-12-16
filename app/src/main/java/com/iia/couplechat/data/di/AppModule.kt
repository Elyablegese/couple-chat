package com.iia.couplechat.data.di

import android.content.Context
import androidx.room.Room
import com.iia.couplechat.data.db.CoupleChatDatabase
import com.iia.couplechat.data.repository.country.CountryRepository
import com.iia.couplechat.data.repository.country.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoupleChatDatabase(@ApplicationContext context: Context): CoupleChatDatabase {
        return Room.databaseBuilder(
            context,
            CoupleChatDatabase::class.java,
            "couple_chat"
        )
            .createFromAsset("database/countries.sqlite")
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface AppModuleBinder {
    @Singleton
    @Binds
    fun bindCountryRepository(countryRepository: CountryRepositoryImpl): CountryRepository
}