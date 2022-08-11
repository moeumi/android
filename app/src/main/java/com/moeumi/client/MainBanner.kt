package com.moeumi.client

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import baseUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.moeumi.client.data_type.ContentData
import com.moeumi.client.data_type.ContentElement
import com.skydoves.landscapist.glide.GlideImage
import getContentUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import readyTo
import org.jsoup.Jsoup
import com.google.gson.Gson
import com.moeumi.client.data_type.ImageData
import com.moeumi.client.data_type.ImageElement

val CARD_PADDING = 33.dp
val HORIZONTAL_PADDING = 16.dp


@Composable
fun GetBannerImage(page: Int?=0){
    val _isEnd = MutableStateFlow(false)
    val isEnd = _isEnd.asStateFlow()
    val parameter = "/logo"
    val url = "$baseUrl${parameter}"
    val gson = Gson()
    CoroutineScope(Dispatchers.IO).launch {
        kotlin.runCatching {
            if (!_isEnd.value) {
                val doc = Jsoup.connect(url).get()
                val body = doc.select("body").text()
                val data : ImageData=
                    gson.fromJson(body, Array<ImageElement>::class.java).toList()
                Log.d("getBanner", data.toString())
                result = data;
                if (data.isEmpty()) {
                    _isEnd.value = true
                    result = data;
                    Log.d("getBanner", "last")
                }
            }
        }.onSuccess {
//                Log.d("getContent", it.toString())
            Log.d("getBanner", url)
        }.onFailure {
            _isEnd.value = true
            Log.d("getBanner", "fail")
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Preview(name = "figma", widthDp = 412, heightDp = 892)
@Composable
fun MainBanner() {
    val context = LocalContext.current
    HorizontalPager(
        count = 3,
        modifier = Modifier
            .padding(top = HORIZONTAL_PADDING, bottom = HORIZONTAL_PADDING)
            .fillMaxWidth()
            .height(180.dp)
    ) { page ->
        for (i in 0 until 3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = CARD_PADDING, end = CARD_PADDING)
//                    .graphicsLayer {
//                        // Calculate the absolute offset for the current page from the
//                        // scroll position. We use the absolute value which allows us to mirror
//                        // any effects for both directions
//                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
//
//                        // We animate the scaleX + scaleY, between 85% and 100%
//                        lerp(
//                            start = ScaleFactor(0.85f, 0.85f),
//                            stop = ScaleFactor(1f, 1f),
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        ).also { scale ->
//                            scaleX = scale.scaleX
//                            scaleY = scale.scaleX
//                        }
//
//                        // We animate the alpha, between 50% and 100%
////                        alpha = lerp(
////                            start = 0.5f,
////                            stop = 1f,
////                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
////                        )
//                    }
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                readyTo,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
            ) {
                Box {
                    GetBannerImage(page)
                    MainBannerText(modifier = Modifier.align(Alignment.BottomStart))
                    MainBannerText(
                        text = "부산도서관",
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp)
                    ) {
                        for (j in 0 until 3) {
                            if (j == page) {
                                MainBannerToggleButton(
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                            } else {
                                MainBannerToggleButton(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                            }
                            Spacer(
                                modifier = Modifier.padding(
                                    horizontal = 4.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainBannerImageView(image_url: String = "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80") {
    GlideImage(
        imageModel = image_url,
//        circularReveal = CircularReveal(duration = 250),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
    )
}

@Composable
fun MainBannerText(
    text: String = "인문학으로 만나는 유럽의 예술",
    fontSize: TextUnit = 28.sp,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = FontWeight.ExtraBold,
        overflow = TextOverflow.Ellipsis,
        fontFamily = notoSanse,
        lineHeight = 38.sp,
        textAlign = textAlign,
        modifier = modifier
            .width(256.dp)
            .padding(16.dp),
    )
}

@Preview
@Composable
fun MainBannerNameText() {
    Box {
        MainBannerText(
            text = "부산도서관",
            fontSize = 16.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview
@Composable
fun MainBannerToggleButton(color: Color = Color.White, shape: Shape = CircleShape) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(shape)
                .background(color)
        )
    }
}
