package com.moeumi.client

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.moeumi.client.dummies.readyTo
import com.skydoves.landscapist.glide.GlideImage

val CARD_PADDING = 33.dp
val VERTICAL_PADDING = 16.dp

@OptIn(ExperimentalPagerApi::class)
@Preview(name = "figma", widthDp = 412, heightDp = 892)
@Composable
fun MainBanner() {
    val context = LocalContext.current
    HorizontalPager(
        count = 3,
        modifier = Modifier
            .padding(top = VERTICAL_PADDING, bottom = VERTICAL_PADDING)
            .fillMaxWidth()
            .height(230.dp)
    ) { page ->
        val mainImage = listOf(
            painterResource(id = R.drawable.main_banner67),
            painterResource(id = R.drawable.main_banner68),
            painterResource(id = R.drawable.main_banner69),
        )
        Image(
            painter = mainImage[page],
            contentDescription = "banner",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast
                        .makeText(
                            context,
                            readyTo,
                            Toast.LENGTH_LONG
                        )
                        .show()
                },
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun MainSubBanner() {
    val subBanner = listOf(
        painterResource(id = R.drawable.subbanner_group73),
        painterResource(id = R.drawable.subbanner_group74),
        painterResource(id = R.drawable.subbanner_group75),
        painterResource(id = R.drawable.subbanner_group76),
        painterResource(id = R.drawable.subbanner_group78),
        painterResource(id = R.drawable.subbanner_group79),
    )
    Column(
        modifier = Modifier.padding(start = CARD_PADDING, end = CARD_PADDING)
    ) {
        MainListTitle("이달의 행사")
        LazyRow(modifier = Modifier.height(280.dp)) {
            itemsIndexed(subBanner) { _, image ->
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
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
