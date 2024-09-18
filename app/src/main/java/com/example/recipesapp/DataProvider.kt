package com.example.recipesapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object DataProvider {
    val recipeList = listOf(
        Recipe(1, "Spaghetti Bolognese", R.drawable.spaghetti, 30, listOf("Spaghetti", "Tomato", "Ground Beef")),
        Recipe(2, "Chicken Curry", R.drawable.chicken_curry, 40, listOf("Chicken", "Curry Powder", "Coconut Milk")),
        Recipe(3, "Beef Stew", R.drawable.beef_stew, 60, listOf("Beef", "Potatoes", "Carrots", "Onions"))
    )

    fun getRecipeById(id: Int) = recipeList.find { it.id == id }
}

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val cookingTime: Int,
    val ingredients: List<String>
) : Parcelable
