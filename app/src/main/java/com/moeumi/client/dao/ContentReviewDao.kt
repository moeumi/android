package com.moeumi.client.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moeumi.client.data.data_type.ContentReview

@Dao
interface ContentReviewDao {
    @Query("SELECT * FROM ContentReview")
    fun getAll(): List<ContentReview>

    @Query("SELECT * FROM ContentReview WHERE contentId IN (:contentId)")
    fun loadAllByIds(contentId: BooleanArray): List<ContentReview>

    @Insert
    fun insertAll(vararg users: ContentReview)

    @Insert
    fun insert(user: ContentReview)

    @Update
    fun updateAll(vararg users: ContentReview)

    @Update
    fun updateFavorite(vararg isFav: ContentReview)
}
