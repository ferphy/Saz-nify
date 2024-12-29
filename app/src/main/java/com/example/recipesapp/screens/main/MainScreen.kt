package com.example.recipesapp.screens.main

import android.text.Spanned
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.widgets.RecipeCard
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.randomModel.RandomRecipes
import com.example.recipesapp.widgets.MainTopAppBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel
){

    //HTML format to readable text
    var parsedHtml: Spanned

    val recipesData = produceState<DataOrException<RandomRecipes, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getRandomRecipes()
    }.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when {
                recipesData.loading == true -> {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                }

                recipesData.data != null -> {
                    Log.d("MainScreen", "MainScreen: ${recipesData.data!!.recipes[1].instructions}")
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(recipesData.data!!.recipes) { recipe ->
                            parsedHtml = HtmlCompat.fromHtml(
                                recipe.summary,
                                HtmlCompat.FROM_HTML_MODE_COMPACT
                            )
                            RecipeCard(
                                title = recipe.title,
                                description = parsedHtml.toString(),
                                image = recipe.image
                            )
                        }
                    }
                }

                recipesData.e != null -> {
                    Log.e("MainScreen", "Error loading recipes: ${recipesData.e}")
                    Text(text = "Error loading recipes")
                }
            }
        }
    }
}