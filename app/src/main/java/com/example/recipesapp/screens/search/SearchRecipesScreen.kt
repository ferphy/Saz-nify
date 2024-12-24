package com.example.recipesapp.screens.search

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.findByIngridientsModel.RecipesList

@Composable
fun SearchRecipesScreen(
    ingredients: String = "apples,+flour,+sugar",
    modifier: Modifier = Modifier,
    searchRecipesViewModel: SearchRecipesViewModel = hiltViewModel(),
    navController: NavHostController

){

    val recipesData = produceState<DataOrException<RecipesList, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = searchRecipesViewModel.getRecipes(ingredients)
    }.value

    Surface(modifier = modifier.fillMaxSize()) {
        when {
            recipesData.loading == true -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
            recipesData.data != null -> {
                Log.d(">SearchScreen", "SearchScreen: ${recipesData.data}")
                Text(text = "Recipes Loaded!")
            }
            recipesData.e != null -> {
                Log.e("SearchScreen", "Error loading recipes: ${recipesData.e}")
                Text(text = "Error loading recipes")
            }
        }
    }

}