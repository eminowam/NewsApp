package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.Repository
import com.example.newsapp.presentation.News

class GetTopHeadlineUseCase(private val repository: Repository) {
    suspend fun execute(query: String, category: String): News =
        repository.getTopHeadlines(query = query, category = category)
}