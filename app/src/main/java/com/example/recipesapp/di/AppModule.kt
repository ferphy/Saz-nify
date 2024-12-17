package com.example.recipesapp.di

import com.example.recipesapp.network.FindByIngredientsAPI
import com.example.recipesapp.network.RandomAPI
import com.example.recipesapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRecipesRepository(): FindByIngredientsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FindByIngredientsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomRecipesRepository(): RandomAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomAPI::class.java)
    }
}