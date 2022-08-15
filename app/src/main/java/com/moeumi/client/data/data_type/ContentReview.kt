package com.moeumi.client.data.data_type

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContentReview(
    @PrimaryKey val contentId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "is_favorite") val isFav: Boolean,
    @ColumnInfo(name = "star_rank") val starRank: Int,
    @ColumnInfo(name = "review") val review: String
)
