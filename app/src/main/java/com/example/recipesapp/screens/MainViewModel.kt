package com.example.recipesapp.screens

import androidx.lifecycle.ViewModel
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.RecipesList
import com.example.recipesapp.repository.FindByIngredientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FindByIngredientsRepository) : ViewModel() {

    suspend fun getRecipes(ingredients: String): DataOrException<RecipesList, Boolean, Exception> {
        return repository.getRecipes(ingredients)
    }
}