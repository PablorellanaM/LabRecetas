package com.example.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipes App") }
            )
        },
        drawerContent = {
            DrawerBody(
                onRecipeClick = {
                    // Navegar a la lista de recetas
                },
                onSavedRecipesClick = {
                    // Navegar a recetas guardadas
                }
            )
        }
    ) {
        // Aquí iría el contenido principal
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipesAppTheme {
        MainScreen()
    }
}
