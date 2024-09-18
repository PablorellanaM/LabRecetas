package com.example.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.RecipesAppTheme

class RecipeListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
                val savedRecipes = remember { mutableStateListOf<Recipe>() }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Popular Recipes") }
                        )
                    }
                ) { innerPadding ->
                    RecipeListScreen(
                        savedRecipes = savedRecipes,
                        onRecipeClick = { selectedRecipe ->
                            val intent = Intent(this, RecipeDetailActivity::class.java)
                            intent.putExtra("RECIPE_ID", selectedRecipe.id)
                            startActivity(intent)
                        },
                        modifier = Modifier.padding(innerPadding) // Aplica el padding aquí
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeListScreen(
    savedRecipes: MutableList<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier // Modificador para manejar el padding
) {
    val recipes = remember { DataProvider.recipeList }
    LazyColumn(modifier = modifier) {
        items(recipes) { recipe ->
            RecipeListItem(recipe, savedRecipes, onClick = { onRecipeClick(recipe) })
        }
    }
}

@Composable
fun RecipeListItem(recipe: Recipe, savedRecipes: MutableList<Recipe>, onClick: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = recipe.name, modifier = Modifier.padding(8.dp))
            Text(text = "${recipe.cookingTime} mins", modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.padding(8.dp)) {
                Button(onClick = {
                    if (!savedRecipes.contains(recipe)) {
                        savedRecipes.add(recipe)
                    }
                }) {
                    Text(text = "Save Recipe")
                }
            }
        }
    }
}
