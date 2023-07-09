package com.example.newsapp.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.R
import com.example.newsapp.domain.Article
import com.squareup.picasso.Picasso

class NewsViewHolder(view: View) : ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.news_image)
    private val title = view.findViewById<TextView>(R.id.news_title)
    fun bind(article: Article) {
        if (article.urlToIage != null) Picasso.get().load(article.urlToIage).into(image)
        title.text = article.title
    }
}