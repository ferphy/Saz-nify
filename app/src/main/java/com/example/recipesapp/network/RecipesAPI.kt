package com.example.recipesapp.network

import com.example.recipesapp.model.RecipesList
import com.example.recipesapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface FindByIngredientsAPI {
    @GET("findByIngredients")
    suspend fun findByIngredients(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = 10
    ): RecipesList
}
