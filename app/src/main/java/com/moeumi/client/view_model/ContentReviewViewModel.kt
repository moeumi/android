package com.moeumi.client.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.moeumi.client.AppDatabase
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
                contentId = 0,
                isFav = false,
                starRank = 0,
                review = ""
            )
        )
    )
    val contentReview = _contentReview.asStateFlow()

//    fun setDb(db: AppDatabase, context: Context) {
//        CoroutineScope(Dispatchers.Main).launch {
//            db
//        }
//    }

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
}
