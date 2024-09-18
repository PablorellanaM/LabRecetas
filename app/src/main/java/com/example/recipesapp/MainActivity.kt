package com.example.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.theme.RecipesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val savedRecipes = remember { mutableStateListOf<Recipe>() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipes App") }
            )
        },
        drawerContent = {
            DrawerBody(
                onRecipeClick = {
                    // Navegar a la lista de recetas populares
                    val context = LocalContext.current
                    context.startActivity(Intent(context, RecipeListActivity::class.java))
                },
                onSavedRecipesClick = {
                    // Navegar a recetas guardadas
                    val context = LocalContext.current
                    val intent = Intent(context, SavedRecipesActivity::class.java)
                    intent.putParcelableArrayListExtra("SAVED_RECIPES", ArrayList(savedRecipes))
                    context.startActivity(intent)
                }
            )
        }
    ) {
        // Pantalla principal vacía, el Drawer maneja la navegación entre actividades
    }
}

@Composable
fun DrawerBody(onRecipeClick: () -> Unit, onSavedRecipesClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextButton(onClick = onRecipeClick) {
            Text(text = "Popular Recipes")
        }
        TextButton(onClick = onSavedRecipesClick) {
            Text(text = "Saved Recipes")
        }
    }
}
