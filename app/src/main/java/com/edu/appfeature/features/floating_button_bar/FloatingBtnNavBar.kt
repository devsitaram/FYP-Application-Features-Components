package com.edu.appfeature.features.floating_button_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edu.appfeature.R
import com.edu.appfeature.ui.util.TextView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingBtnNavBarViewScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                elevation = 22.dp
            ) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center, // Uncomment this line
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    Screen.Camera.route.let {
                        navController.navigate(it) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    Screen.Camera.route.let { navController.navigate(it) }
                },
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = "Add icon")
            }
        }
    ) {
        MainScreenNavigation(navController)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        elevation = 0.dp
    ) {
        items.forEach {
            BottomNavigationItem(
                icon = {
                    it.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                        )
                    }
                },
                label = {
                    it.title?.let {
                        Text(
                            text = it,
                        )
                    }
                },
                selected = currentRoute?.hierarchy?.any { it.route == it.route } == true,

                selectedContentColor = Color(R.color.purple_700),
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    it.route.let { it1 ->
                        navController.navigate(it1) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun MainScreenNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = Screen.Profile.route) {

        //profile
        composable(Screen.Profile.route) {
             ProfileScreen()
        }
        //pickUp
        composable(Screen.PickUp.route) {
             PickupScreen()
        }

        //camera
        composable(Screen.Camera.route) {
             CameraScreen()
        }
    }
}

@Composable
fun CameraScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue), contentAlignment = Alignment.Center){
        Text(text = "CameraScreen", color = Color.White)
    }
}

@Composable
fun PickupScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Green), contentAlignment = Alignment.Center){
        Text(text = "PickupScreen", color = Color.White)
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Red), contentAlignment = Alignment.Center){
        Text(text = "ProfileScreen", color = Color.White)
    }
}
