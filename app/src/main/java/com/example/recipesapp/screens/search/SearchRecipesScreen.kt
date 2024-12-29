package com.example.recipesapp.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.recipesapp.widgets.SearchTopAppBar

@Composable
fun SearchRecipesScreen(
    viewModel: AutocompleteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val query = remember { mutableStateOf(TextFieldValue("")) }

    val recipesData by viewModel.recipes.collectAsState()

    Scaffold{ innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
                SearchTopAppBar(
                    valueState = query,
                    placeHolder = "Search",
                    onValueChange = { text ->
                        viewModel.getAutocomplete(text)
                    }
                )

            Spacer(modifier = Modifier.height(8.dp))

            when {
                recipesData.loading == true -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                recipesData.e != null -> {
                    Text(
                        text = "Error at loading recipes",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                recipesData.data != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(recipesData.data ?: emptyList()) { recipe ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(AppScreens.DetailsRecipeScreen.name + "/${recipe.id}")
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                                Text(
                                    text = recipe.title,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
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
