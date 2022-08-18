package com.moeumi.client

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moeumi.client.dummies.readyTo

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            LazyColumn {
                item { MainAppBar() }
                item { MainBanner() }
                item { MainCategoryGroup() }
                item { MainSubBanner() }
                item {
                    GetLocationPermission(
                        content = { MainList() },
                        requestCompose = {
                            Text(
                                text = "주변의 행사를 찾기 위해 위치 권한이 필요해요.\n아래 버튼을 눌러 위치 권한을 부여해주세요.",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp),
                            )
                        }

                    )
                }
            }
        }
        composable(route = BottomBarScreen.Favorite.route) {
            Favorite()
        }
        composable(route = BottomBarScreen.Organize.route) {
            val context = LocalContext.current
            val organizeList = listOf("화명도서관", "부전도서관", "부산시립미술관", "금련산청소년수련원")
            val organizeImage = listOf(
                painterResource(id = R.drawable.hwamyung),
                painterResource(id = R.drawable.bujeon),
                painterResource(id = R.drawable.busan_art),
                painterResource(id = R.drawable.kumlyun)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                CategoryContentListAppBar("기관")
                Box(modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color(92, 92, 92, 35))
                    .clickable {
                        Toast
                            .makeText(context, readyTo, Toast.LENGTH_SHORT)
                            .show()
                    }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_oui_search_24),
                            contentDescription = "검색 아이콘",
                            modifier = Modifier.padding(16.dp),
                        )
                        Text(text = "검색어를 입력하세요", color = Color(92, 92, 92))
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(organizeList) { index, title ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(79.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                readyTo,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = organizeImage[index],
                                    contentDescription = title,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(end = 32.dp)
                                        .size(64.dp)
                                )
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Text(
                                        text = title,
                                        fontFamily = notoSanse,
                                        fontWeight = Bold,
                                        modifier = Modifier.offset(y = 4.dp),
                                    )
                                    Text(
                                        text = "리뷰 0",
                                        fontFamily = notoSanse,
                                        fontWeight = Medium,
                                        modifier = Modifier.offset(y = (-16).dp),
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.CenterEnd)
                                        .absoluteOffset()
                                        .width(92.dp)
                                        .background(
                                            color = Color(255, 91, 91, 91),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable {
                                            Toast
                                                .makeText(context, readyTo, Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                        .wrapContentSize(Alignment.Center),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "팔로우",
                                        fontFamily = notoSanse,
                                        fontWeight = Bold,
                                        color = Color(233, 40, 40)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
