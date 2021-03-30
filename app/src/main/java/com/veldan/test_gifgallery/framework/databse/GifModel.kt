package com.veldan.test_gifgallery.framework.databse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif_table")
data class GifModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "url_fixed_width")
    val urlFixedWidth: String,

    @ColumnInfo(name = "url_fixed_height")
    val urlFixedHeight: String
)















