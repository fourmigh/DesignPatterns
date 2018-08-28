package org.caojun.designpatterns.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.caojun.designpatterns.R
import org.caojun.designpatterns.fragment.BuilderFragment
import org.caojun.designpatterns.fragment.PrincipleFragment
import org.caojun.designpatterns.fragment.PrototypeFragment
import org.caojun.designpatterns.fragment.SingletonFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PrincipleFragment()
            1 -> SingletonFragment()
            2 -> BuilderFragment()
            3 -> PrototypeFragment()
            else -> PrincipleFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.principle)
            1,2,3,4,5,6 -> {
                val designPatterns = context.resources.getStringArray(R.array.design_patterns)
                designPatterns[position - 1]
            }
            else -> context.getString(R.string.principle)
        }
    }
}