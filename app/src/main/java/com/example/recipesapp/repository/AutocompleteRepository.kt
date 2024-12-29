package com.example.recipesapp.repository

import android.util.Log
import com.example.recipesapp.data.DataOrException
import com.example.recipesapp.model.autocompleteModel.AutocompleteList
import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.network.AutocompleteAPI
import javax.inject.Inject

class AutocompleteRepository @Inject constructor(private val api: AutocompleteAPI) {
    suspend fun getAutocomplete(query: String): DataOrException<AutocompleteList, Boolean, Exception> {
        val response = try {
            Log.d("AutocompleteRepo", "getAutocomplete: $query")
            api.getAutocomplete(query = query)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}
