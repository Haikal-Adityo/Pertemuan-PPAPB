package com.example.pertemuan8_tugas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pertemuan8_tugas.LoginFragment
import com.example.pertemuan8_tugas.RegisterFragment

class TabAdapter(fm:FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {

    private val page = arrayOf<Fragment>(RegisterFragment(), LoginFragment())

    override fun getItemCount(): Int {
        return page.size
    }

    override fun createFragment(position: Int): Fragment {
        return page[position]
    }

}