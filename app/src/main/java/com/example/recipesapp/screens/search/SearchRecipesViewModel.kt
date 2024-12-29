package com.example.recipesapp.screens.search

import androidx.lifecycle.ViewModel
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.repository.FindByIngredientsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchRecipesViewModel @Inject constructor(private val repository: FindByIngredientsRepository) : ViewModel() {

    suspend fun getRecipes(ingredients: String): DataOrException<RecipesList, Boolean, Exception> {
        return repository.getRecipes(ingredients)
    }
}