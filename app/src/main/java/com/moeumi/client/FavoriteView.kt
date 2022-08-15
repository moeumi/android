package com.moeumi.client

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.moeumi.client.view_model.ContentReviewViewModel

const val contentReviewDbName = "content-review"

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun Favorite(contentReviewViewModel: ContentReviewViewModel = ContentReviewViewModel()) {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, contentReviewDbName
    ).build()

    contentReviewViewModel.getAll(db)
    val contentReview by contentReviewViewModel.contentReview.collectAsState()

    Column {
        CategoryContentListAppBar("저장목록")
        Divider(
            modifier = Modifier
                .alpha(0.5f)
                .padding(
                    top = MainPadding / 2,
                    bottom = MainPadding / 2,
                    start = MainPadding / 4,
                    end = MainPadding / 4
                )
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            itemsIndexed(contentReview) { _, item ->
                Content(
                    title = item.title,
                    place = item.place,
                    date = item.date,
                    url = item.url,
                    contentId = item.contentId
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}
