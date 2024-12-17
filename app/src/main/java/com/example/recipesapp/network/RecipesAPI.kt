package com.example.recipesapp.network

import com.example.recipesapp.model.findByIngridientsModel.RecipesList
import com.example.recipesapp.model.randomModel.RandomRecipes
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

interface RandomAPI {
    @GET("random")
    suspend fun findByIngredients(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("include-tags") includeTags: String = "",
        @Query("exclude-tags") excludeTags: String = "",
        @Query("number") number: Int = 10
    ): RandomRecipes
}

