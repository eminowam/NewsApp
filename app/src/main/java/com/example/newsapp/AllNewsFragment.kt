package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentAllNewsBinding
import com.example.newsapp.presentation.DetailsNewsActivity
import com.example.newsapp.presentation.MainActivityViewModel
import com.example.newsapp.presentation.NewsAdapter
import com.example.newsapp.presentation.NewsSetOnClickListener

class AllNewsFragment : Fragment(), NewsSetOnClickListener, AdapterView.OnItemSelectedListener {
    private val binding by lazy {
        FragmentAllNewsBinding.inflate(layoutInflater)
    }
    private val _categoriesEn by lazy {
        listOf<String>("popularity", "relevancy", "publishedAt")
    }
    private val spinnerAdapter by lazy {
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, _categoriesEn)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]

    }

    private val newsAdapter by lazy {
        NewsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()
        binding.allNewsRv.adapter = newsAdapter
        viewModel.articles.observe(viewLifecycleOwner) { articleList ->
            newsAdapter.news = articleList
        }
        binding.spinner.onItemSelectedListener = this
        binding.spinner.adapter = spinnerAdapter
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) viewModel.changeSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) viewModel.changeSearch(newText)
                return false
            }
        })
    }

    override fun onNewsSetOnClickListener(url: String) {
        startActivity(
            DetailsNewsActivity.startIntentToDetailsNewsActivity(
                requireContext(), url
            )
        )
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.changeCategory(newCategory = _categoriesEn[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}