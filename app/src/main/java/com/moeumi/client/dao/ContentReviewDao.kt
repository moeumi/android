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
    fun loadAllByIds(contentId: IntArray): List<ContentReview>

    @Insert
    fun insertAll(vararg users: ContentReview)

    @Insert
    fun insert(user: ContentReview)

    @Update
    fun updateAll(vararg users: ContentReview)

    @Query("UPDATE ContentReview SET is_favorite=:isFav WHERE contentId=:cid")
    fun updateFavorite(isFav: Boolean, cid: Int)
}
