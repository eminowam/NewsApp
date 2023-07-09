package com.example.newsapp.data

import com.example.newsapp.presentation.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET(Utils.EVERYTHING)
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("domains") domains: String = "bbc.com , euronews.com , edition.cnn com , news.google.com , aljazeera.com",
        @Query("apiKey") apiKey: String? = Utils.API_KEY,
    ): News

    @GET(Utils.TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("domains") domains: String = "bbc.com , euronews.com , edition.cnn com , news.google.com , aljazeera.com",
        @Query("apiKey") apiKey: String? = Utils.API_KEY,
    ): News

}