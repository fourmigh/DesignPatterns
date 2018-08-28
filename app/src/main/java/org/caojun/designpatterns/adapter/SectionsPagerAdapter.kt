package org.caojun.designpatterns.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.caojun.designpatterns.R
import org.caojun.designpatterns.fragment.*

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PrincipleFragment()
            1 -> SingletonFragment()
            2 -> BuilderFragment()
            3 -> PrototypeFragment()
            4 -> FactoryFragment()
            else -> PrincipleFragment()
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            in 1 .. 23 -> {
                val designPatterns = context.resources.getStringArray(R.array.design_patterns)
                designPatterns[position - 1]
            }
            else -> context.getString(R.string.principle)
        }
    }
}