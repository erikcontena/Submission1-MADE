package com.contena.favorite.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.contena.favorite.movie.FavMovieFragment
import com.contena.favorite.tvshow.FavTvShowFragment

class FavoriteSectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavMovieFragment()
            1 -> fragment = FavTvShowFragment()
        }
        return fragment as Fragment
    }
}