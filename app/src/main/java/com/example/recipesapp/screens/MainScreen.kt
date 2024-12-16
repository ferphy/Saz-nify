package com.example.recipesapp.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.RecipesList

@Composable
fun MainScreen(
    ingredients: String = "apples,+flour,+sugar",
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
    ){

    val recipesData = produceState<DataOrException<RecipesList, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getRecipes(ingredients)
    }.value

    Surface(modifier = modifier.fillMaxSize()) {
        when {
            recipesData.loading == true -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
            recipesData.data != null -> {
                Log.d("MainScreen", "MainScreen: ${recipesData.data}")
                Text(text = "Recipes Loaded!")
            }
            recipesData.e != null -> {
                Log.e("MainScreen", "Error loading recipes: ${recipesData.e}")
                Text(text = "Error loading recipes")
            }
        }
    }

}