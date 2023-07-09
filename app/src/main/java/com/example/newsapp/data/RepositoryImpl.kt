package com.example.newsapp.data

import com.example.newsapp.domain.repository.Repository
import com.example.newsapp.presentation.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl : Repository {
    private val api = RetrofitInstance.api

    override suspend fun getEverythingNews(query: String, sortBy: String): News =
        withContext(Dispatchers.IO) { api.getEverything(query, sortBy) }

    override suspend fun getTopHeadlines(query: String, category: String): News =
        withContext(Dispatchers.IO) { api.getTopHeadlines(query = query, category = category)}

}