package com.droidProject.breakingbad.adadpters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.droidProject.breakingbad.ui.CharacterFragment
import com.droidProject.breakingbad.ui.EpisodeFragment
import com.droidProject.breakingbad.ui.QuoteFragment

class TabAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {

                return CharacterFragment()
            }
            1 -> {
                return EpisodeFragment()
            }
            2 -> {

                return QuoteFragment()
            }
            else -> CharacterFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}