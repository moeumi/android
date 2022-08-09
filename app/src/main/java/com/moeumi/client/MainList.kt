package com.moeumi.client

import android.content.Intent
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val MainPadding = 16.dp

@Preview
@Composable
fun MainList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MainPadding,
                bottom = MainPadding,
                start = CARD_PADDING,
                end = CARD_PADDING
            )
    ) {
        MainListTitle()
        for (i in 0..10) {
            Content()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun MainListTitle(title: String = "내 주변 프로그램") {
//    MainBannerText(
//        title,
//        28.sp,
//        color = Color.Black,
//        textAlign = TextAlign.Start,
//        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp)
//    )
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
fun Content(title: String = "<8월 금요시네마: 100% 울프: 푸들이 될 순 없어>") {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape((38 / 1.7).dp))
            .clickable {
                val intent = Intent(context, WebViewActivity::class.java)
                context.startActivity(intent)
                Toast
                    .makeText(context, "Item selected", Toast.LENGTH_SHORT)
                    .show()
            }
            .background(Color(0xFFF5F5F5))
            .fillMaxWidth()
            .height((115).dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                title,
                fontSize = 16.sp,
//                fontWeight = Black,
                modifier = Modifier,
                fontFamily = notoSanse,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .clip(shape = RoundedCornerShape((11).dp))
                    .background(color = Color(parseColor("#FF7979")))
            ) {
                Text(
                    text = "만덕도서관\t\t2022-08-31",
                    fontWeight = Medium,
                    fontFamily = notoSanse,
                    color = Color(parseColor("#525252")),
                    modifier = Modifier
                        .fillMaxSize()
                        //                        .padding(4.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}
