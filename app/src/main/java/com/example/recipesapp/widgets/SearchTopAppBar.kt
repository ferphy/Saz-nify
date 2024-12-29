package com.example.recipesapp.widgets
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchTopAppBar() {

    val ctx = LocalContext.current
    var query by mutableStateOf("")
    var active by mutableStateOf(false)
    SearchBar(
        query = query,
        onQueryChange = { query = it} ,
        onSearch = {
            Toast.makeText(ctx, query, Toast.LENGTH_SHORT).show()
            active = false
        },
        active = active,
        onActiveChange = { active = it}
    ) {
        if(query.isNotEmpty()){

        }

    }
}