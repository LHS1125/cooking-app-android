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
    private val MAXNUM = 999
    private var startNum = 0
    private lateinit var response: Response<SearchResponse>

    init {
        viewModelScope.launch {
            response = bookSearchRepository.searchRecipesList(1, 1000)

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(0, 4))
                }
            } else {
                Log.d(TAG, "searchBooks: response.isNotSuccessful")
                Log.d(TAG, response.message())
            }
        }
    }

    fun addNum() {
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
                _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+4))
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