package com.moeumi.client

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.moeumi.client.data.data_type.ContentReview
import com.moeumi.client.view_model.ContentReviewViewModel
import com.moeumi.client.view_model.GetContentViewModel
import com.moeumi.client.view_model.GetCurrentLocationViewModel

val MainPadding = 16.dp

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun MainList(
    currentLocationViewModel: GetCurrentLocationViewModel = GetCurrentLocationViewModel(),
    contentViewModel: GetContentViewModel = GetContentViewModel()
) {
    val context = LocalContext.current
    currentLocationViewModel.getCurrentDistrict(context)
    val lat by currentLocationViewModel.latitude.collectAsState()
    val longi by currentLocationViewModel.longitude.collectAsState()
    Log.d("lat", lat.toString())
    currentLocationViewModel.getDistrict(lat.toString(), longi.toString())

    val district by currentLocationViewModel.district.collectAsState()
    val contentList by contentViewModel.content.collectAsState()
//    val contentList = remember { contentListState }

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(900.dp)
            .padding(
                top = MainPadding,
                bottom = MainPadding,
                start = CARD_PADDING,
                end = CARD_PADDING
            ),
    ) {

        item {
            MainListTitle("내주변 프로그램")
        }

        itemsIndexed(contentList) { index, item ->
            var page = 1
            kotlin.runCatching {
                Content(
                    title = item.contents_title,
                    place = item.center_name,
                    date = item.apply_end_date,
                    url = item.detail_link,
                    contentId = item.contents_id.toInt(),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (index == contentList.lastIndex) {
                contentViewModel.getContent(
                    page,
                    parameter = "/district/${district}"
                )
                if (district != "더미데이터") {
                    page += 1
                }
            }
        }
    }
}

@Preview
@Composable
fun MainListTitle(title: String = "내 주변 프로그램", fontSize: TextUnit = 28.sp) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Text(
        text = title,
        fontSize = fontSize,
        color = textColor,
        fontWeight = FontWeight.ExtraBold,
        overflow = TextOverflow.Ellipsis,
        fontFamily = notoSanse,
        lineHeight = 38.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    )
}

@Preview
@Composable
fun Content(
    title: String,
    place: String,
    date: String,
    url: String,
    contentId: Int
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("url", url)
                context.startActivity(intent)
            }
            .background(Color(parseColor("#ebebeb")))
            .fillMaxWidth()
            .height(115.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row {
                Spacer(
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    modifier = Modifier,
                    fontFamily = notoSanse,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            ContentDetailPlanView(place = place, date = date, contentId = contentId)
        }
    }
}

@Preview
@Composable
fun ContentDetailPlanView(
    place: String,
    date: String,
    contentId: Int,
    contentReviewViewModel: ContentReviewViewModel = ContentReviewViewModel()
) {
    val context = LocalContext.current
    val textColor = if (isSystemInDarkTheme()) {
        Color.Black
    } else {
        Color(parseColor("#525252"))
    }
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        contentReviewDbName,
    ).build()

    contentReviewViewModel.getAll(db)
    contentReviewViewModel._isFavoriteQuery(db, contentId)
    val isFavorite by contentReviewViewModel.isFavorite.collectAsState()

    val heartImage = if (!isFavorite) {
        R.drawable.ic_iconmonstr_favorite_no
    } else {
        R.drawable.ic_iconmonstr_favorite_fill
    }

    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Row(
            modifier = Modifier
                .width(250.dp)
                .clip(shape = RoundedCornerShape((11).dp))
                .background(color = Color(parseColor("#FF7979"))),
        ) {
            Text(
                text = place,
                fontWeight = Medium,
                fontFamily = notoSanse,
                color = textColor,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(135.dp),
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = date,
                fontWeight = Medium,
                fontFamily = notoSanse,
                color = textColor,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape((11).dp))
                    .background(color = Color(parseColor("#FF7979"))),
            )
        }
        IconButton(
            onClick = {
                contentReviewViewModel.insert(
                    db,
                    ContentReview(contentId = contentId, isFav = true, starRank = 0, review = "")
                )
                Log.d("insert", "insert")
            },
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 38.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(heartImage)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
