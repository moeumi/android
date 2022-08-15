package com.moeumi.client

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.contruct),
                    contentDescription = "준비중입니다.",
                    modifier = Modifier
                        .size(256.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
