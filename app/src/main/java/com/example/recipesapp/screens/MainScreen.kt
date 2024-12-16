package com.example.recipesapp.screens

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.RecipesList

@Composable
fun MainScreen(
    ingredients: String = "apples,+flour,+sugar",
    modifier: Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
    ){

    val recipesData = produceState<DataOrException<RecipesList, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getRecipes(ingredients)
    }.value

    if (recipesData.loading == true) {
        CircularProgressIndicator()
    } else if(recipesData.data != null) {
        Log.d("MainScreen", "MainScreen: ${recipesData.data}")
    }

}