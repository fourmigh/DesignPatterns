package org.caojun.designpatterns.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.caojun.designpatterns.fragment.SingletonFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SingletonFragment()
            else -> SingletonFragment()
        }
    }

    override fun getCount(): Int {
        return 1
    }
}