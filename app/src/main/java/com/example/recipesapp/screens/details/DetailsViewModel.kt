package com.example.recipesapp.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.finByID.RecipeByID
import com.example.recipesapp.repository.FindByIDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: FindByIDRepository
) : ViewModel() {
    // Flujo para almacenar el estado de búsqueda
    private val _recipes =
        MutableStateFlow<DataOrException<RecipeByID, Boolean, Exception>>(
            DataOrException(loading = false)
        )
    val recipes: StateFlow<DataOrException<RecipeByID, Boolean, Exception>> = _recipes

    // Método para buscar recetas en tiempo real
    fun getRecipeByID(recipeId: String) {
        Log.d("DetailsViewModel", "Recipe ID: $recipeId")
        viewModelScope.launch {
            _recipes.value = DataOrException(loading = true)
            try {
                val result = repository.getAutocomplete(recipeId)
                _recipes.value = result
            } catch (e: Exception) {
                _recipes.value = DataOrException(e = e)
            }
        }
    }
}