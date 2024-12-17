package com.example.recipesapp.repository

import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.model.randomModel.RandomRecipes
import com.example.recipesapp.network.RandomAPI
import javax.inject.Inject

class RandomRepository @Inject constructor(private val api: RandomAPI) {

    suspend fun getRandomRecipes(): DataOrException<RandomRecipes, Boolean, Exception> {
        val response = try {
            api.findByIngredients()
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}

