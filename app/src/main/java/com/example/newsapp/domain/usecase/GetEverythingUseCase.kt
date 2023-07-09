package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.Repository
import com.example.newsapp.presentation.News

class GetEverythingUseCase(private val repository: Repository) {
    suspend fun execute(q: String, sort: String): News = repository.getEverythingNews(q, sort)
}