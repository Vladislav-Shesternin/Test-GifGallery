package com.veldan.test_gifgallery.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {

    @Query("SELECT * FROM gif_table")
    fun getAllGifs(): List<GifModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGif(gif: GifModel)

    @Query("DELETE FROM gif_table")
    suspend fun clear()

}