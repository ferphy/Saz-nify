package com.example.recipesapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipesapp.screens.main.MainScreen
import com.example.recipesapp.screens.main.MainViewModel
import com.example.recipesapp.screens.search.SearchRecipesScreen
import com.example.recipesapp.screens.splash.SplashScreen

@Composable
fun AppNavigation (){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.name
    ) {
        composable(AppScreens.SplashScreen.name) {
            SplashScreen(navController)
        }

        composable(AppScreens.MainScreen.name) {backStackEntry ->
            val mainViewModel = hiltViewModel<MainViewModel>(backStackEntry)

            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

        composable(AppScreens.SearchRecipesScreen.name) {
            SearchRecipesScreen(navController = navController)
        }
    }

}