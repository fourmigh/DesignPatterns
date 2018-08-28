package org.caojun.designpatterns.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_builder.*
import kotlinx.android.synthetic.main.layout_advantages_disadvantages.*
import org.caojun.designpatterns.R
import org.caojun.designpatterns.utils.ArrayUtils

/**
 * 原型模式
 */
class PrototypeFragment: Fragment() {

    public class WordDocument: Cloneable {
        private var mText = ""
        private val mImages = ArrayList<String>()

        constructor() {
            Log.d("WordDocument", "构造函数")
        }

        override fun clone(): Any {
            val doc = super.clone() as WordDocument
            doc.mText = this.mText
            doc.mImages.clear()
            doc.mImages.addAll(this.mImages.clone() as ArrayList<String>)
            return doc
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public class WordDocument: Cloneable {\n" +
                "        private var mText = \"\"\n" +
                "        private val mImages = ArrayList<String>()\n" +
                "        \n" +
                "        constructor() {\n" +
                "            Log.d(\"WordDocument\", \"构造函数\")\n" +
                "        }\n" +
                "\n" +
                "        override fun clone(): Any {\n" +
                "            val doc = super.clone() as WordDocument\n" +
                "            doc.mText = this.mText\n" +
                "            doc.mImages.clear()\n" +
                "            doc.mImages.addAll(this.mImages.clone() as ArrayList<String>)\n" +
                "            return doc\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.prototype_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.prototype_disadvantages)
    }
}