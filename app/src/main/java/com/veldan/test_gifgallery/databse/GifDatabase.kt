package com.veldan.test_gifgallery.databse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GifModel::class],
    version = 1,
    exportSchema = false
)
abstract class GifDatabase : RoomDatabase() {

    abstract val gifDao: GifDao

}