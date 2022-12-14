@file:OptIn(ExperimentalMaterial3Api::class)

package com.moeumi.client

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moeumi.client.dummies.readyTo

@Preview
@Composable
fun MainAppBar(address: String = readyTo) {
//    val context = LocalContext.current
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.contruct),
                contentDescription = "메인 AppBar",
                modifier = Modifier.size(96.dp)
            )
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(
//                    text = address,
//                    fontWeight = FontWeight.Bold,
//                    fontFamily = notoSanse,
//                    modifier = Modifier,
//                )
//                IconButton(
//                    onClick = { Toast.makeText(context, readyTo, Toast.LENGTH_SHORT).show() },
//                    modifier = Modifier,
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_baseline_oui_keyboard_arrow_down_24),
//                        contentDescription = "주소변경"
//                    )
//                }
//            }
        }
    )
}

@Preview
@Composable
fun ContentAppBar(
    titleText: String = "Moeumi"
) {
    val context = LocalContext.current
    val activity = context as Activity
    SmallTopAppBar(
        title = { Text(titleText) },
        modifier = Modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    activity.finish()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_oui_back_24),
                    contentDescription = "Back",
                    modifier = Modifier
                )
            }
        }
    )
}

@Preview
@Composable
fun CategoryContentListAppBar(titleText: String = "카테고리") {
    val activity = LocalContext.current as Activity

    Column {
        Spacer(modifier = Modifier.height(52.dp))
        SmallTopAppBar(
            title = {
                Text(
                    titleText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { activity.finish() },
                    modifier = Modifier.padding(start = 0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_oui_back_24),
                        contentDescription = "카테고리변경"
                    )
                }
            }
        )
    }
}
