package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.recipesapp.screens.search.SearchRecipesScreen
import com.example.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchRecipesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
