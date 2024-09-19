package com.example.recipesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


object DataProvider {
    val recipeList = listOf(
        Recipe(
            id = 1,
            name = "Spaghetti Bolognese",
            imageRes = R.drawable.spaghetti, // Asegúrate de tener estas imágenes en res/drawable
            cookingTime = 30,
            ingredients = listOf("Spaghetti", "Tomato", "Ground Beef", "Onion", "Garlic")
        ),
        Recipe(
            id = 2,
            name = "Chicken Curry",
            imageRes = R.drawable.chicken_curry, // Asegúrate de tener estas imágenes en res/drawable
            cookingTime = 40,
            ingredients = listOf("Chicken", "Curry Powder", "Coconut Milk", "Onions", "Garlic", "Ginger")
        ),
        Recipe(
            id = 3,
            name = "Beef Stew",
            imageRes = R.drawable.beef_stew, // Asegúrate de tener estas imágenes en res/drawable
            cookingTime = 60,
            ingredients = listOf("Beef", "Potatoes", "Carrots", "Onions", "Celery", "Garlic")
        )
    )

    fun getRecipeById(id: Int): Recipe? {
        return recipeList.find { it.id == id }
    }
}

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val cookingTime: Int,
    val ingredients: List<String>
) : Parcelable
