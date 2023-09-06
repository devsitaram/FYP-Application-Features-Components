package com.edu.appfeature.features.navehostwithdatapass

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edu.appfeature.features.paraller.ParallaxViewScreen
import com.edu.appfeature.features.searchpage.SearchViewScreen

@Composable
fun NavigateDataPassViewScreen(navHostController: NavHostController) {

    val dataViewModel: DataViewModel = viewModel() // data pass from one page to another page

    NavHost(navController = navHostController, startDestination = "Search_Screen") {
        composable("Search_Screen") {
            SearchViewScreen(navHostController, dataViewModel) // give data
        }
        composable("Parallax_Screen") {
            ParallaxViewScreen(navHostController, dataViewModel) // received data
        }
    }
}