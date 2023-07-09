package com.example.newsapp

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentTopNewsBinding
import com.example.newsapp.presentation.*

class TopNewsFragment : Fragment(), NewsSetOnClickListener, AdapterView.OnItemSelectedListener {

    private var _binding: FragmentTopNewsBinding? = null
    private val binding: FragmentTopNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentTopNewsBinding == null")

    private val categoriesEn by lazy {
        listOf<String>("business", "entertainment", "general", "health", "science", "technology")
    }
    private val spinnerAdapter by lazy {
        ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categoriesEn)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TopHeadlinesViewModel::class.java]
    }

    private val newsAdapter by lazy {
        NewsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopHeadlines()
        binding.topNewsRv.adapter = newsAdapter
        viewModel.articlesTop.observe(viewLifecycleOwner) { articles ->
            newsAdapter.news = articles
        }
        binding.spinner.onItemSelectedListener = this
        binding.spinner.adapter = spinnerAdapter
        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

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
        viewModel.changeCategory(newCategory = categoriesEn[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}

