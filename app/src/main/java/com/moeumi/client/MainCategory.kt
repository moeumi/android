package com.moeumi.client

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CATEGORY_GROUP_PADDING = 8.dp

@Preview
@Composable
fun MainCategoryGroup() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
//            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth()
                    .padding(CATEGORY_GROUP_PADDING)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MainCategory(width = (128 + 64).dp)
                MainCategory(width = (128 + 64).dp)
            }
            Row(
                modifier = Modifier
                    .height(128.dp)
                    .fillMaxWidth()
                    .padding(CATEGORY_GROUP_PADDING)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MainCategory()
                MainCategory()
                MainCategory()
            }
        }
    }
}

@Preview
@Composable
fun MainCategory(title: String = "전체", width: Dp = 128.dp) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .height(128.dp)
            .width(width)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.Black)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Category selceted",
                        Toast.LENGTH_LONG
                    )
                    .show()
            }
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
//                .clip(shape = RoundedCornerShape(18.dp))
                .padding(8.dp)
                .align(Alignment.Center),
        )
    }
}
