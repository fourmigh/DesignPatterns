package org.caojun.designpatterns.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_singleton.*
import org.caojun.designpatterns.R

/**
 * 单例模式
 */
class SingletonFragment: Fragment() {

    private class Singleton1 {
        companion object {
            private var mInstance: Singleton1? = null

            fun getInstance(): Singleton1 {
                if (mInstance == null) {
                    synchronized(Singleton1.javaClass) {
                        if (mInstance == null) {
                            mInstance = Singleton1()
                        }
                    }
                }
                return mInstance!!
            }
        }

        constructor()

        fun doSomething() {
            Log.d("Singleton1", "do sth.")
        }
    }

    private class Singleton2 {
        private constructor()

        companion object {
            private class SingletonHolder {
                companion object {
                    internal val mInstance = Singleton2()
                }
            }

            fun getInstance(): Singleton2 {
                return SingletonHolder.mInstance
            }
        }

        fun doSomething() {
            Log.d("Singleton2", "do sth.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_singleton, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Singleton1.getInstance().doSomething()
        Singleton2.getInstance().doSomething()

        tvSingleton1.text = "    private class Singleton1 {\n" +
                "        companion object {\n" +
                "            private var mInstance: Singleton1? = null\n" +
                "\n" +
                "            fun getInstance(): Singleton1 {\n" +
                "                if (mInstance == null) {\n" +
                "                    synchronized(Singleton1.javaClass) {\n" +
                "                        if (mInstance == null) {\n" +
                "                            mInstance = Singleton1()\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "                return mInstance!!\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        constructor()\n" +
                "\n" +
                "        fun doSomething() {\n" +
                "            Log.d(\"Singleton1\", \"do sth.\")\n" +
                "        }\n" +
                "    }"

        tvSingleton2.text = "    private class Singleton2 {\n" +
                "        private constructor()\n" +
                "\n" +
                "        companion object {\n" +
                "            private class SingletonHolder {\n" +
                "                companion object {\n" +
                "                    internal val mInstance = Singleton2()\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            fun getInstance(): Singleton2 {\n" +
                "                return SingletonHolder.mInstance\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        fun doSomething() {\n" +
                "            Log.d(\"Singleton2\", \"do sth.\")\n" +
                "        }\n" +
                "    }"
    }
}