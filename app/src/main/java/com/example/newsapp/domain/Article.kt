package com.example.newsapp.domain


import com.google.gson.annotations.SerializedName


class Article(
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val contentUrl: String,
    @SerializedName("urlToImage") val urlToIage: String,
    @SerializedName("content") val content: String,
)