package com.moeumi.client

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.absoluteValue

val CARD_PADDING = 16.dp
val HORIZONTAL_PADDING = 16.dp

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MainBanner() {
    HorizontalPager(
        count = 3,
        modifier = Modifier
            .padding(top = HORIZONTAL_PADDING, bottom = HORIZONTAL_PADDING)
            .fillMaxWidth()
            .height(200.dp)
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
            ) {
                Box {
                    MainBannerImageView()
                    MainBannerText(modifier = Modifier.align(Alignment.BottomStart))
                    MainBannerText(
                        text = "부산도서관",
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
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
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        overflow = TextOverflow.Ellipsis,
        fontFamily = FontFamily.Cursive,
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
