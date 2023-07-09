package com.example.newsapp.domain.repository

import com.example.newsapp.presentation.News

interface Repository {
    suspend fun getEverythingNews(query: String, sortBy: String): News
    suspend fun getTopHeadlines(query: String,category:String): News
}