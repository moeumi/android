package com.moeumi.client

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val MainPadding = 16.dp

@Composable
fun MainList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MainPadding, bottom = MainPadding)
    ) {
        MainListTitle()
        for (i in 0..10) {
            Content()
        }
    }
}

@Preview
@Composable
fun MainListTitle(title: String = "내 주변 프로그램") {
    MainBannerText(title, 28.sp, color = Color.Black, textAlign = TextAlign.Start)
}

@Preview
@Composable
fun Content(title: String = "[유아 동화구연] 아동이랑 재동이랑") {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(16.dp)
            .clickable {
                val intent = Intent(context, WebViewActivity::class.java)
                context.startActivity(intent)
                Toast
                    .makeText(context, "Item selected", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    title, fontSize = 22.sp, fontWeight = Bold, modifier = Modifier,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(color = Color.Yellow)
//                        .alpha(0.5f)
                        .height(28.dp)
                ) {
                    Text(
                        text = "만덕도서관\t2022-08-31 시작",
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }
        }
    }
}
