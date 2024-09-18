package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.RecipesAppTheme

class RecipeDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        val recipe = DataProvider.getRecipeById(recipeId)

        setContent {
            RecipesAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(recipe?.name ?: "Recipe Detail") })
                    }
                ) {
                    RecipeDetailScreen(recipe)
                }
            }
        }
    }
}

@Composable
fun RecipeDetailScreen(recipe: Recipe?) {
    recipe?.let {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Ingredients", style = MaterialTheme.typography.h5)
            recipe.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient", modifier = Modifier.padding(4.dp))
            }
        }
    }
}
