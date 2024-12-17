package com.example.recipesapp.screens.main

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
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.model.randomModel.RandomRecipes
import com.example.recipesapp.screens.search.SearchRecipesViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
){

    val recipesData = produceState<DataOrException<RandomRecipes, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getRandomRecipes()
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