package com.example.newsapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.newsapp.databinding.ActivityDetailsNewsBinding

class DetailsNewsActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailsNewsBinding.inflate(layoutInflater)
    }

    private val url: String by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringExtra(URL) as String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.webview.loadUrl(url)
        binding.webview.webViewClient = MyWebViewClient(url)
    }
    companion object {
        private const val URL = "URL"
        fun startIntentToDetailsNewsActivity(context: Context, url: String): Intent =
            Intent(context, DetailsNewsActivity::class.java).apply {
                bundleOf().apply { putExtra(URL, url) }
            }
    }
}