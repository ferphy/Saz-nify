package com.example.recipesapp.repository

import android.util.Log
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.autocompleteModel.AutocompleteList
import com.example.recipesapp.model.finByID.RecipeByID
import com.example.recipesapp.network.AutocompleteAPI
import com.example.recipesapp.network.FindByIdAPI
import javax.inject.Inject

class FindByIDRepository @Inject constructor(private val api: FindByIdAPI) {
    suspend fun getAutocomplete(recipeId: String): DataOrException<RecipeByID, Boolean, Exception> {
        val response = try {
            api.getAutocomplete(recipeId = recipeId)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}