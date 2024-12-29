package com.example.recipesapp.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.autocompleteModel.AutocompleteList
import com.example.recipesapp.model.randomModel.RandomRecipes
import com.example.recipesapp.widgets.RecipeCard

@Composable
fun SearchRecipesScreen(
    viewModel: AutocompleteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    // Estado para el texto ingresado
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }

    // Observa el estado del ViewModel
    val recipesData by viewModel.recipes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de texto para búsqueda
        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.getAutocomplete(it.text) // Actualizar resultados al escribir
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(focusRequester),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar resultados
        when {
            recipesData.loading == true -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            recipesData.e != null -> {
                Log.e("MainScreen", "Error: ${recipesData.e}")
                Text(
                    text = "Error al cargar recetas",
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
                        Text(
                            text = recipe.title,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            else -> {
                Text(
                    text = "No hay resultados",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Mostrar el teclado automáticamente al abrir la pantalla
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }
}
