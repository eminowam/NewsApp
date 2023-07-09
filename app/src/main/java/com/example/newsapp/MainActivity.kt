package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.MainActivityViewModel
import com.example.newsapp.presentation.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewPagerAdapter = MyViewPagerAdapter(
            listOf(AllNewsFragment(), TopNewsFragment()),
            lifecycle,
            supportFragmentManager
        )
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.isSaveEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_all)
                1 -> tab.setIcon(R.drawable.ic_tops)
            }
        }.attach()
    }
}