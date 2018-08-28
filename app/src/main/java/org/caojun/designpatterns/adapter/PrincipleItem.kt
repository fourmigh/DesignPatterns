package org.caojun.designpatterns.adapter

import android.view.View
import org.caojun.adapter.bean.AdapterItem
import org.caojun.designpatterns.R
import org.caojun.designpatterns.bean.Principle
import org.caojun.expandtextview.ExpandTextView

class PrincipleItem: AdapterItem<Principle> {

    private lateinit var expandTextView: ExpandTextView

    override fun getLayoutResId(): Int {
        return R.layout.adapter_principle
    }

    override fun bindViews(root: View) {
        expandTextView = root as ExpandTextView
    }

    override fun setViews() {
    }

    override fun handleData(t: Principle, position: Int) {
        expandTextView.title = t.label
        expandTextView.content = t.content
    }
}