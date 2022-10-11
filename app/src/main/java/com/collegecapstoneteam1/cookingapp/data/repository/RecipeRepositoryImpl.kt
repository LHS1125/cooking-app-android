package com.collegecapstoneteam1.cookingapp.data.repository

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.collegecapstoneteam1.cookingapp.data.api.RetrofitInstance
import com.collegecapstoneteam1.cookingapp.data.model.SearchResponse
import retrofit2.Response

class RecipeRepositoryImpl : RecipeRepository {
    override suspend fun searchRecipesList(startIdx: Int, endIdx: Int): Response<SearchResponse> {
        return RetrofitInstance.api.searchRecipesList(startIdx, endIdx)
    }

    override suspend fun searchRecipes(
        startIdx: Int,
        endIdx: Int,
        recipeName: String,
<<<<<<< HEAD
    ): Response<SearchResponse> {
        return RetrofitInstance.api.searchRecipes(startIdx, endIdx, recipeName)
=======
        recipeDetail: String
    ): Response<SearchResponse> {
        return RetrofitInstance.api.searchRecipes(startIdx, endIdx, recipeName, recipeDetail)
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
    }


}