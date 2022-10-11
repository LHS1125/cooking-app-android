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
    private var MAXNUM = 999
    private val PERPAGE = 5
    private var startNum = 0
    private lateinit var response: Response<SearchResponse>

    init {
        viewModelScope.launch {
            response = bookSearchRepository.searchRecipesList(1, 1000)

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    bookSearchRepository.searchRecipesList(1001, 2000).body()?.let {
                        body.cOOKRCP01.recipes += it.cOOKRCP01.recipes
                    }
                    MAXNUM = body.cOOKRCP01.recipes.size - 1
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(0, PERPAGE))
                }
            } else {
                Log.d(TAG, "searchBooks: response.isNotSuccessful")
                Log.d(TAG, response.message())
            }
        }
    }

    /** ㅅㄷㄴㅅ  */
    fun addNum() {
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
                var lastPerPage = body.cOOKRCP01.recipes.size%5
                if (MAXNUM - startNum < 5){
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+lastPerPage))
                }else{
                    _searchResult.postValue(body.cOOKRCP01.recipes.subList(startNum, startNum+PERPAGE))
                }
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