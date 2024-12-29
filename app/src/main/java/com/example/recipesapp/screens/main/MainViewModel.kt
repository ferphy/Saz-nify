package com.example.recipesapp.screens.main

import androidx.lifecycle.ViewModel
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.model.randomModel.RandomRecipes
import com.example.recipesapp.repository.FindByIngredientsRepository
import com.example.recipesapp.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RandomRepository) : ViewModel(){

    suspend fun getRandomRecipes(): DataOrException<RandomRecipes, Boolean, Exception> {
        return repository.getRandomRecipes()
    }
}
