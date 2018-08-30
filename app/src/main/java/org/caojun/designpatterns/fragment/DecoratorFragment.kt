package org.caojun.designpatterns.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import org.caojun.designpatterns.R

/**
 * 装饰模式
 */
class DecoratorFragment: Fragment() {

    public class DecoratorActivity: Activity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //初始化数据
            initData()

            //初始化控件
            initViews()

            //初始化事件
            initEvent()
        }

        /**
         * 初始化数据
         */
        private fun initData() {

        }

        /**
         * 初始化控件
         */
        private fun initViews() {

        }

        /**
         * 初始化事件
         */
        private fun initEvent() {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = getString(R.string.decorator, "    public class DecoratorActivity: Activity() {\n" +
                "        \n" +
                "        override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "            super.onCreate(savedInstanceState)\n" +
                "            \n" +
                "            //初始化数据\n" +
                "            initData()\n" +
                "\n" +
                "            //初始化控件\n" +
                "            initViews()\n" +
                "            \n" +
                "            //初始化事件\n" +
                "            initEvent()\n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * 初始化数据\n" +
                "         */\n" +
                "        private fun initData() {\n" +
                "            \n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * 初始化控件\n" +
                "         */\n" +
                "        private fun initViews() {\n" +
                "            \n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * 初始化事件\n" +
                "         */\n" +
                "        private fun initEvent() {\n" +
                "            \n" +
                "        }\n" +
                "    }")
        val scrollView = ScrollView(context)
        scrollView.addView(textView)
        return scrollView
    }
}