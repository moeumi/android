package com.moeumi.client

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moeumi.client.dao.ContentReviewDao
import com.moeumi.client.data.data_type.ContentReview

@Database(entities = [ContentReview::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentReviewDao(): ContentReviewDao
}