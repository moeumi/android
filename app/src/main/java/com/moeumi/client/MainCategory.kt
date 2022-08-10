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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import readyTo


@Preview(name = "figma", widthDp = 412, heightDp = 892)
@Composable
fun MainCategoryGroup() {
    Box(
        modifier = Modifier
            .padding(horizontal = CARD_PADDING)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MainCategory(title = "전체", width = (82.4 * 2).dp)
                Spacer(modifier = Modifier.width(16.dp))
                MainCategory(title = "독서인문", width = (82.4 * 2).dp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MainCategory(title = "자기계발", width = 102.81.dp, modifier = Modifier.weight(1f))
                MainCategory(title = "체험", width = 102.81.dp, modifier = Modifier.weight(1f))
                MainCategory(title = "유아", width = 102.81.dp, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun MainCategory(title: String = "전체", width: Dp = 128.dp, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .height(82.dp)
            .width(width)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.Black)
            .clickable {
                Toast
                    .makeText(context, readyTo, Toast.LENGTH_SHORT)
                    .show()
//                context.startActivity(
//                    Intent(context, CategoryContentList::class.java)
//                )
            }
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = notoSanse,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
        )
    }
}
