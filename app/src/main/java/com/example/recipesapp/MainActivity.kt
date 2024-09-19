package com.example.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
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
    val context = LocalContext.current // Obtenemos el contexto dentro de la función composable

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipes App") }
            )
        },
        drawerContent = {
            DrawerBody(
                onRecipeClick = {
                    // Usamos el contexto obtenido dentro de @Composable
                    context.startActivity(Intent(context, RecipeListActivity::class.java))
                },
                onSavedRecipesClick = {
                    // Navegar a recetas guardadas
                    val intent = Intent(context, SavedRecipesActivity::class.java)
                    intent.putParcelableArrayListExtra("SAVED_RECIPES", ArrayList(savedRecipes))
                    context.startActivity(intent)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Welcome to Recipes App", modifier = Modifier.padding(16.dp))
        }
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
