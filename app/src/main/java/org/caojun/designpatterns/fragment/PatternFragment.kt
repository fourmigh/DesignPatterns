package org.caojun.designpatterns.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_principle.*
import org.caojun.designpatterns.MainActivity
import org.caojun.designpatterns.R

/**
 * 模式列表
 */
class PatternFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_principle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val patterns = resources.getStringArray(R.array.design_patterns)
//        val list = ArrayList<Principle>()
//        for (i in 0 until principles.size) {
//            val principle = principles[i].split(":")
//            val label = principle[0]
//            val resId = resources.getIdentifier(principle[1], "string", activity?.packageName)
//            list.add(Principle(label, activity?.getString(resId)))
//        }

        listView.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, patterns)
        listView.setOnItemClickListener { parent, view, position, id ->
            val act = activity as MainActivity
            act.setCurrentItem(position + 2)
        }
    }
}