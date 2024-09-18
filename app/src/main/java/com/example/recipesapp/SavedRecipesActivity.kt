package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.RecipesAppTheme

class SavedRecipesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedRecipes: ArrayList<Recipe>? = intent.getParcelableArrayListExtra("SAVED_RECIPES")

        setContent {
            RecipesAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Saved Recipes") })
                    }
                ) {
                    savedRecipes?.let {
                        SavedRecipesScreen(it)
                    } ?: Text(text = "No saved recipes")
                }
            }
        }
    }
}

@Composable
fun SavedRecipesScreen(savedRecipes: List<Recipe>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(savedRecipes) { recipe ->
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = recipe.name)
                    Text(text = "${recipe.cookingTime} mins")
                }
            }
        }
    }
}
