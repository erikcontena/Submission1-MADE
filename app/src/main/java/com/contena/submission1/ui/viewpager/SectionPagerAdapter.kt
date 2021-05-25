package com.contena.submission1.ui.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.contena.submission1.ui.movie.MovieFragment
import com.contena.submission1.ui.tvshow.TvShowFragment

class SectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }
}