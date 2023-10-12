package com.edu.appfeature.features.floating_button_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenList(val route: String){
    object Post: ScreenList("post")
}

sealed class BtnNavScreen(val route: String, val title: String?, val icon: ImageVector?) {
    object Home : BtnNavScreen("home", "Home", Icons.Default.Home)
    object Profile : BtnNavScreen("profile", "Profile", Icons.Default.Person)
    object PickUp : BtnNavScreen("pickup", "PickUp", Icons.Default.ShoppingCart)
    object Setting : BtnNavScreen("setting", "Setting", Icons.Default.Settings)
}

// items for bottom nav
val items = listOf(
    BtnNavScreen.Home,
    BtnNavScreen.Profile,
    null,
    BtnNavScreen.PickUp,
    BtnNavScreen.Setting
)