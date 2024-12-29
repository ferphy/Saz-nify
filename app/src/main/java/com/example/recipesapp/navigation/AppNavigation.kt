package com.example.recipesapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesapp.screens.details.DetailsRecipeScreen
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
        val route = AppScreens.DetailsRecipeScreen.name
        composable("$route/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.StringType
            })){ NavBack ->
            NavBack.arguments?.getString("recipeId")?.let { recipeId ->
                Log.d("AppNavigation", "Recipe ID: $recipeId")
                DetailsRecipeScreen(navController = navController, recipeId = recipeId)
            }
        }
    }

}