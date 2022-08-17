package com.moeumi.client

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(name = "figma", widthDp = 412, heightDp = 892)
@Composable
fun MainCategoryGroup() {
    Box(
        modifier = Modifier.padding(start = CARD_PADDING, end = CARD_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                color = Color(0, 0, 0, 0)
            ) {
                MainCategoryCircle(
                    title = "전체",
                    paint = painterResource(R.drawable.ic_ico_all),
                    modifier = Modifier
                )
            }
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                color = Color(0, 0, 0, 0)
            ) {
                MainCategoryCircle(
                    title = "독서인문",
                    paint = painterResource(R.drawable.ic_ico_read),
                    modifier = Modifier
                )
            }
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                color = Color(0, 0, 0, 0)
            ) {
                MainCategoryCircle(
                    title = "체험",
                    paint = painterResource(R.drawable.ic_ico_exp),
                    modifier = Modifier
                )
            }
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                color = Color(0, 0, 0, 0)
            ) {
                MainCategoryCircle(
                    title = "유아",
                    paint = painterResource(R.drawable.ic_ico_kid),
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview
@Composable
fun MainCategoryCircle(title: String, paint: Painter, modifier: Modifier) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            val settings = Intent(context, CategoryContentList::class.java);
            settings.putExtra("category", title);
            context.startActivity(
                settings
            )
        },
        modifier = Modifier
            .border(
                color = Color(29, 29, 29),
                width = 1.dp,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(Color(29, 29, 29))
            .padding(2.dp)
    ) {
        Icon(
            painter = paint,
            contentDescription = null,
            modifier = modifier,/*.clip(CircleShape)*/
            tint = Color.White,
        )
    }
}
