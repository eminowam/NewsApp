package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.domain.Article
import com.example.newsapp.presentation.adapter.NewsViewHolder

interface NewsSetOnClickListener{
    fun onNewsSetOnClickListener(url:String)
}
class NewsAdapter(private val listener: NewsSetOnClickListener): RecyclerView.Adapter<NewsViewHolder>() {

    var news = emptyList<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context, R.anim.animation
            )
        )
        holder.itemView.setOnClickListener{
            listener.onNewsSetOnClickListener(news[position].contentUrl)
        }
    }
}