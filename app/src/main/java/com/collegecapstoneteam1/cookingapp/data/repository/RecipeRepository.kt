package com.collegecapstoneteam1.cookingapp.data.repository

import com.collegecapstoneteam1.cookingapp.data.model.SearchResponse
import retrofit2.Response

interface RecipeRepository {
    suspend fun searchRecipesList(
        startIdx: Int,
        endIdx: Int,
    ): Response<SearchResponse>

    suspend fun searchRecipes(
        startIdx: Int,
        endIdx: Int,
        recipeName: String,
<<<<<<< HEAD
=======
        recipeDetail: String
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
    ): Response<SearchResponse>


}