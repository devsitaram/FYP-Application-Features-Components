package com.edu.appfeature.features.floating_button_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val title: String?, val icon: ImageVector?) {
    object PickUp : Screen("pickup", "PickUp", Icons.Default.ShoppingCart)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Camera:Screen("camera",null,null)

}

// items for bottom nav
val items = listOf(
    Screen.PickUp,
    Screen.Profile
)