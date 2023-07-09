package com.example.newsapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(
    private val fragmentList: List<Fragment>,
    lifecycle: Lifecycle,
    fm: FragmentManager
) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragments = fragmentList
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
