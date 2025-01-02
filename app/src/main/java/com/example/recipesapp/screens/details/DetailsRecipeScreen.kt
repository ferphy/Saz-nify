package com.example.recipesapp.screens.details

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.navigation.AppScreens
import com.example.recipesapp.screens.search.AutocompleteViewModel
import com.example.recipesapp.widgets.SearchTopAppBar

@Composable
fun DetailsRecipeScreen(
    navController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel(),
    recipeId: String
) {
    Log.d("DetailsRecipeScreen", "Recipe ID: $recipeId")
    viewModel.getRecipeByID(recipeId)

    val query = remember { mutableStateOf(TextFieldValue("")) }
    val recipesData by viewModel.recipes.collectAsState()


    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()), // Agrega el scroll vertical
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            when {
                recipesData.loading == true -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                recipesData.e != null -> {
                    Text(
                        text = "Error at loading the recipe",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                recipesData.data != null -> {
                    Text(
                        text = recipesData.data!!.title,
                        modifier = Modifier.padding(16.dp)
                    )

                }

                else -> {
                    Text(
                        text = "No recipes found",
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
        }
    }
}