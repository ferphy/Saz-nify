package com.example.recipesapp.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailsRecipeScreen(navController: NavHostController, recipeId: String) {

    Text(text = "Details Screen el id de la receta es $recipeId")
}