package com.example.newsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.RepositoryImpl
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.usecase.GetTopHeadlineUseCase
import kotlinx.coroutines.launch

class TopHeadlinesViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    private val getTopHeadlineUseCase = GetTopHeadlineUseCase(repository)

    private val _articlesTop: MutableLiveData<List<Article>> = MutableLiveData()
    val articlesTop: LiveData<List<Article>> get() = _articlesTop

    private val _category: MutableLiveData<String> = MutableLiveData("entertainment")
    private val _search: MutableLiveData<String> = MutableLiveData("")

    fun getTopHeadlines() = viewModelScope.launch {
        _articlesTop.value =
            getTopHeadlineUseCase.execute(
                _search.value.toString(),
                _category.value.toString()
            ).articles
    }

    fun changeCategory(newCategory: String) {
        _category.value = newCategory
        getTopHeadlines()
    }


    fun changeSearch(newSearch: String) {
        _search.value = newSearch
        getTopHeadlines()

    }

}