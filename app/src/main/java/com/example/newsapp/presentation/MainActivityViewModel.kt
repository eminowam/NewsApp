package com.example.newsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.RepositoryImpl
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.usecase.GetEverythingUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    private val getEverythingUseCase = GetEverythingUseCase(repository)

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> get() = _articles

    private val _category: MutableLiveData<String> = MutableLiveData("popularity")
    private val _search: MutableLiveData<String> = MutableLiveData("popularity")


    fun getNews() = viewModelScope.launch {
        _articles.value = getEverythingUseCase.execute(
            _search.value.toString(),
            _category.value.toString()
        ).articles
    }

    fun changeCategory(newCategory: String) {
        _category.value = newCategory
        getNews()
    }


    fun changeSearch(newSearch: String) {
        _search.value = newSearch
        getNews()

    }
}