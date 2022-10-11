package com.collegecapstoneteam1.cookingapp.data.api

import com.collegecapstoneteam1.cookingapp.data.model.SearchResponse
import com.collegecapstoneteam1.cookingapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeSearchApi {
    @GET("/api/{API_KEY}/COOKRCP01/json/{startIdx}/{endIdx}")
    suspend fun searchRecipesList(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("API_KEY") api_key : String = API_KEY,
    ): Response<SearchResponse>

<<<<<<< HEAD
    @GET("/api/{API_KEY}/COOKRCP01/json/{startIdx}/{endIdx}/RCP_NM={RCP_NM}")
    suspend fun searchRecipes(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("RCP_NM") recipeName: String = "",
=======
    @GET("/api/{API_KEY}/COOKRCP01/json/{startIdx}/{endIdx}")
    suspend fun searchRecipes(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Query("RCP_NM") recipeName: String,
        @Query("RCP_PARTS_DTLS") recipeDetail: String,
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
        @Path("API_KEY") api_key : String = API_KEY,
    ): Response<SearchResponse>

}