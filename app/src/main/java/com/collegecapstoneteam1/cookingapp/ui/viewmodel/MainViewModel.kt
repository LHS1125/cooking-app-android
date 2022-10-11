package com.collegecapstoneteam1.cookingapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collegecapstoneteam1.cookingapp.data.model.Recipe
import com.collegecapstoneteam1.cookingapp.data.model.SearchResponse
import com.collegecapstoneteam1.cookingapp.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val bookSearchRepository: RecipeRepository
) : ViewModel() {
    private val _searchResult = MutableLiveData<List<Recipe>>()
    val searchResult: LiveData<List<Recipe>> get() = _searchResult
    private val MINNUM = 0
<<<<<<< HEAD
    private var MAXNUM = 999
    private val PERPAGE = 5
=======
    private val MAXNUM = 999
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
    private var startNum = 0
    private lateinit var response: Response<SearchResponse>

    init {
        viewModelScope.launch {
            response = bookSearchRepository.searchRecipesList(1, 1000)

            if (response.isSuccessful) {
                response.body()?.let { body ->
<<<<<<< HEAD
                    MAXNUM = body.cOOKRCP01.recipes.size - 1
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(0, PERPAGE))
=======
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(0, 4))
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
                }
            } else {
                Log.d(TAG, "searchBooks: response.isNotSuccessful")
                Log.d(TAG, response.message())
            }
        }
    }

    fun addNum() {
<<<<<<< HEAD
        if(startNum+PERPAGE <= MAXNUM)startNum += PERPAGE
    }

    fun decreaseNum() {
        if (startNum-PERPAGE >= MINNUM) startNum -= PERPAGE
    }

    fun searchRecipes(
        recipeName: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        response = bookSearchRepository.searchRecipes(1, 15, recipeName)

        if (response.isSuccessful) {
            response.body()?.let { body ->
                startNum = 0
                MAXNUM = body.cOOKRCP01.recipes.size - 1
                var lastPerPage = body.cOOKRCP01.recipes.size%PERPAGE

                if (MAXNUM - startNum < PERPAGE){
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+lastPerPage))
                }else{
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+PERPAGE))
                }

=======
        if(startNum != MAXNUM)startNum += 5
    }

    fun decreaseNum() {
        if (startNum != MINNUM) startNum -= 5

    }

    fun searchRecipes(
        startIdx: Int,
        endIdx: Int,
        recipeName: String,
        recipeDetail: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+4))
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
            }
        } else {
            Log.d(TAG, "searchBooks: response.isNotSuccessful")
            Log.d(TAG, response.message())
        }
    }

    fun searchRecipesList(
    ) = viewModelScope.launch(Dispatchers.IO) {

        if (response.isSuccessful) {
            response.body()?.let { body ->
<<<<<<< HEAD
                var lastPerPage = body.cOOKRCP01.recipes.size%5
                if (MAXNUM - startNum < 5){
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+lastPerPage))
                }else{
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+PERPAGE))
                }
=======
                _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+4))
>>>>>>> 486c591389c772b6890b9a2fd46a02c78b1b8488
            }
        } else {
            Log.d(TAG, "searchBooks: response.isNotSuccessful")
            Log.d(TAG, response.message())
        }
    }


    companion object {
        private const val TAG = "MainViewModel"
    }

}