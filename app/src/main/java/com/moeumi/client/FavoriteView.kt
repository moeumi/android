package com.moeumi.client

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.moeumi.client.view_model.ContentReviewViewModel

const val contentReviewDbName = "content-review"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Favorite(contentReviewViewModel: ContentReviewViewModel = ContentReviewViewModel()) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, contentReviewDbName
    ).build()

    contentReviewViewModel.getAll(db)
    val contentReview by contentReviewViewModel.contentReview.collectAsState()

    LazyColumn() {
        itemsIndexed(contentReview) { index, contentReview ->
            Text(text = contentReview.isFav.toString())
        }
    }
}
