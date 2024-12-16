package com.example.recipesapp.repository


import android.util.Log
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.RecipesList
import com.example.recipesapp.network.FindByIngredientsAPI
import javax.inject.Inject

class FindByIngredientsRepository @Inject constructor(private val api: FindByIngredientsAPI) {

    suspend fun getRecipes(ingredients: String): DataOrException<RecipesList, Boolean, Exception> {
        val response = try {
            api.findByIngredients(ingredients = ingredients)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}

