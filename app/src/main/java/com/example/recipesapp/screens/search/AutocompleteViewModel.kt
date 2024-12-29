package com.example.recipesapp.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.autocompleteModel.AutocompleteList
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.repository.AutocompleteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutocompleteViewModel @Inject constructor(
    private val repository: AutocompleteRepository
) : ViewModel() {

    // Flujo para almacenar el estado de búsqueda
    private val _recipes =
        MutableStateFlow<DataOrException<AutocompleteList, Boolean, Exception>>(
            DataOrException(loading = false)
        )
    val recipes: StateFlow<DataOrException<AutocompleteList, Boolean, Exception>> = _recipes

    // Método para buscar recetas en tiempo real
    fun getAutocomplete(query: String) {
        viewModelScope.launch {
            _recipes.value = DataOrException(loading = true)
            try {
                val result = repository.getAutocomplete(query)
                _recipes.value = result
            } catch (e: Exception) {
                _recipes.value = DataOrException(e = e)
            }
        }
    }
}
