package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.RecipesAppTheme

class RecipeDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipeId = intent.getIntExtra("RECIPE_ID", -1) // Obtener el ID de la receta seleccionada
        val recipe = DataProvider.getRecipeById(recipeId) // Obtener la receta correspondiente

        setContent {
            RecipesAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(recipe?.name ?: "Recipe Detail") }
                        )
                    }
                ) { innerPadding ->
                    RecipeDetailScreen(
                        recipe = recipe,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeDetailScreen(recipe: Recipe?, modifier: Modifier = Modifier) {
    recipe?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            recipe.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient", modifier = Modifier.padding(4.dp))
            }
        }
    } ?: run {
        Text(text = "Recipe not found", modifier = Modifier.padding(16.dp))
    }
}
