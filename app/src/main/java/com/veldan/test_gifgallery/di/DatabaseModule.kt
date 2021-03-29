package com.veldan.test_gifgallery.di

import android.content.Context
import androidx.room.Room
import com.veldan.test_gifgallery.databse.GifDao
import com.veldan.test_gifgallery.databse.GifDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GifDatabase {
        return Room.databaseBuilder(
            appContext,
            GifDatabase::class.java,
            "gif_database"
        ).build()
    }

    @Provides
    fun provideGifDao(database: GifDatabase): GifDao {
        return database.gifDao
    }
}