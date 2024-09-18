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
        val savedRecipes: ArrayList<Recipe>? = intent.getParcelableArrayListExtra("SAVED_RECIPES") // Obtener las recetas guardadas

        setContent {
            RecipesAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Saved Recipes") }
                        )
                    }
                ) { innerPadding ->
                    savedRecipes?.let {
                        SavedRecipesScreen(
                            recipes = it,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } ?: run {
                        NoSavedRecipesScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun SavedRecipesScreen(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(recipes) { recipe ->
            SavedRecipeItem(recipe)
        }
    }
}

@Composable
fun SavedRecipeItem(recipe: Recipe) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.name, style = MaterialTheme.typography.h6)
            Text(text = "${recipe.cookingTime} mins", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun NoSavedRecipesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "No saved recipes", style = MaterialTheme.typography.h6)
    }
}
