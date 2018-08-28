package org.caojun.designpatterns.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.caojun.adapter.CommonAdapter
import org.caojun.adapter.bean.AdapterItem
import kotlinx.android.synthetic.main.fragment_principle.*
import org.caojun.designpatterns.R
import org.caojun.designpatterns.adapter.PrincipleItem
import org.caojun.designpatterns.bean.Principle

/**
 * 灵活软件六大原则
 */
class PrincipleFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_principle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val principles = resources.getStringArray(R.array.principles)
        val list = ArrayList<Principle>()
        for (i in 0 until principles.size) {
            val principle = principles[i].split(":")
            val label = principle[0]
            val resId = resources.getIdentifier(principle[1], "string", activity?.packageName)
            list.add(Principle(label, activity?.getString(resId)))
        }

        listView.adapter = object : CommonAdapter<Principle>(list, 1) {
            override fun createItem(type: Any?): AdapterItem<*> {
                return PrincipleItem()
            }
        }
    }
}