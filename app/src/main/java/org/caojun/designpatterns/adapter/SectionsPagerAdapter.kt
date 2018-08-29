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
            1 -> PatternFragment()
            2 -> SingletonFragment()
            3 -> BuilderFragment()
            4 -> PrototypeFragment()
            5 -> FactoryFragment()
            6 -> AbstractFactoryFragment()
            7 -> StrategyFragment()
            8 -> StateFragment()
            9 -> IteratorFragment()
            10 -> InterpreterFragment()
            11 -> CommandFragment()
            else -> PrincipleFragment()
        }
    }

    override fun getCount(): Int {
        return 12
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            1 -> context.getString(R.string.pattern)
            in 2 .. 24 -> {
                val designPatterns = context.resources.getStringArray(R.array.design_patterns)
                designPatterns[position - 2]
            }
            else -> context.getString(R.string.principle)
        }
    }
}