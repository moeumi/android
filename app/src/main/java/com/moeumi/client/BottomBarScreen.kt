package com.moeumi.client

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Favorite : BottomBarScreen(
        route = "Favorite",
        title = "Favorite",
        icon = R.drawable.ic_favorite
    )

    object Organize : BottomBarScreen(
        route = "Organize",
        title = "Organize",
        icon = R.drawable.ic_organize
    )
}
