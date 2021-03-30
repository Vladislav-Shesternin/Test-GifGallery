package com.veldan.test_gifgallery.framework.databse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GifModel::class],
    version = 1,
    exportSchema = false
)
abstract class GifDatabase : RoomDatabase() {

    abstract val gifDao: GifDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: GifDatabase? = null
//
//        fun getDatabase(context: Context): GifDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room
//                    .databaseBuilder(
//                        context.applicationContext,
//                        GifDatabase::class.java,
//                        "gif_database"
//                    )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }

}