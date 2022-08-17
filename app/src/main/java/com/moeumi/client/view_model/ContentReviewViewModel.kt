package com.moeumi.client.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.moeumi.client.AppDatabase
import com.moeumi.client.dao.ContentReviewDao
import com.moeumi.client.data.data_type.ContentReview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContentReviewViewModel : ViewModel() {
    private val _contentReview = MutableStateFlow(
        listOf(
            ContentReview(
                title = "",
                place = "",
                date = "",
                url = "",
                contentId = 0,
                isFav = false,
                starRank = 0,
                review = ""
            )
        )
    )
    val contentReview = _contentReview.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun changeIsFavorite(db:AppDatabase, cid: Int) {
        _isFavorite.value=!_isFavorite.value
        Log.d("isFav", _isFavorite.value.toString())
        CoroutineScope(Dispatchers.IO).async {
            val reviewDao = db.contentReviewDao()
            reviewDao.updateFavorite(_isFavorite.value, cid);
        }
    }

    fun getAll(db: AppDatabase) {
        CoroutineScope(Dispatchers.IO).async {
            _contentReview.value =
                db.contentReviewDao().getAll()
        }.onAwait
        Log.d("getAll", _contentReview.value.toString())
    }

    fun insert(db: AppDatabase, contentReview: ContentReview) {
        CoroutineScope(Dispatchers.IO).async {
            val reviewDao = db.contentReviewDao()
            reviewDao.insert(contentReview)
        }.onAwait
    }

    fun _isFavoriteQuery(db: AppDatabase, contentId: Int) {
        val reviewDao = db.contentReviewDao()
        CoroutineScope(Dispatchers.IO).async {
            val review = reviewDao.loadAllByIds(intArrayOf(contentId))
            if (review.isNotEmpty()) {
                _isFavorite.value = review[0].isFav
            } else {
                _isFavorite.value = false
            }
        }.onAwait
    }
}
