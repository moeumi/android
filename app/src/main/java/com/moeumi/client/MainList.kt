package com.moeumi.client

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moeumi.client.view_model.GetContentViewModel
import com.moeumi.client.view_model.GetCurrentLocationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

val MainPadding = 16.dp

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun MainList(
    currentLocationViewModel: GetCurrentLocationViewModel = GetCurrentLocationViewModel(),
    contentViewModel: GetContentViewModel = GetContentViewModel()
) {
    val context = LocalContext.current
    val getLocationScope = CoroutineScope(Dispatchers.Default).async {
        currentLocationViewModel.getCurrentLocation(context)
        currentLocationViewModel.getCurrentAddress(context)
    }
    val address by currentLocationViewModel.address.collectAsState()
    getLocationScope.onAwait.also {
        contentViewModel.getContent(parameter = "/district/${address.region2DepthName}")
    }
    val isEnd by contentViewModel.isEnd.collectAsState()

    val contentList by contentViewModel.content.collectAsState()

//    LaunchedEffect(address) {
//        contentViewModel.getContent(parameter = "/district/${address.region2DepthName}")
//    }

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

        if (contentList.isNotEmpty() || contentList[0].center_name == "더미데이터") {
            itemsIndexed(contentList) { index, item ->
                var page = 2
                kotlin.runCatching {
                    Content(
                        title = item.contents_title,
                        place = item.center_name,
                        date = item.apply_end_date,
                        url = item.detail_link
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                if (index == contentList.lastIndex) {
                    contentViewModel.getContent(
                        page,
                        parameter = "/district/${address.region2DepthName}"
                    )
                    page += 1
                }
            }
        }
    }
}

@Preview
@Composable
fun MainListTitle(title: String = "내 주변 프로그램") {
    Text(
        text = title,
        fontSize = 28.sp,
        color = Color.Black,
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
    title: String = "<8월 금요시네마: 100% 울프: 푸들이 될 순 없어>",
    place: String,
    date: String,
    url: String
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("url", url)
                context.startActivity(intent)
                Toast
                    .makeText(context, "Item selected", Toast.LENGTH_SHORT)
                    .show()
            }
            .background(Color(parseColor("#ebebeb")))
            .fillMaxWidth()
            .height((115).dp)
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
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    modifier = Modifier,
                    fontFamily = notoSanse,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            ContentDetailPlanView(place = place, date = date)
        }
    }
}

@Preview
@Composable
fun ContentDetailPlanView(place: String, date: String) {
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
            color = Color(parseColor("#525252")),
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
            color = Color(parseColor("#525252")),
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            modifier = Modifier
                .clip(shape = RoundedCornerShape((11).dp))
                .background(color = Color(parseColor("#FF7979"))),
        )
    }
}
