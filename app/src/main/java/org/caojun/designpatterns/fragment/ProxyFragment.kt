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
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * 代理模式
 */
class ProxyFragment: Fragment() {

    public class DynamicProxy: InvocationHandler {
        private lateinit var any: Any

        constructor(any: Any) {
            this.any = any
        }

        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any {
            return method.invoke(any, args)
        }
    }

    public interface ILawsuit {
        //提交申请
        fun submit()

        //进行举证
        fun burden()

        //开始辩护
        fun defend()

        //诉讼完成
        fun finish()
    }

    public class XiaoMin: ILawsuit {
        override fun submit() {
            //老板欠小民工资，小民只好申请仲裁
            Log.d("XiaoMin", "老板拖欠工资！特此申请仲裁！")
        }

        override fun burden() {
            //小民证据充足，不怕告不赢
            Log.d("XiaoMin", "这是合同书和过去一年的银行工资流水！")
        }

        override fun defend() {
            //铁证如山，辩护也没什么好说的
            Log.d("XiaoMin", "证据确凿！不需要再说什么了！")
        }

        override fun finish() {
            //结果也是肯定的，必赢
            Log.d("XiaoMin", "诉讼成功！判决老板即日起七天内结算工资")
        }
    }

    public class Client {
        companion object {
            public fun main(args: Array<String>) {
                //构造一个小民
                val xiaomin = XiaoMin()

                //构造一个动态代理
                val proxy = DynamicProxy(xiaomin)

                //获取被代理类小民的ClassLoader
                val loader = xiaomin.javaClass.classLoader

                //动态构造一个代理者律师
                val lawyer = Proxy.newProxyInstance(loader, arrayOf(ILawsuit::class.java), proxy) as ILawsuit

                //律师提交诉讼申请
                lawyer.submit()

                //律师进行举证
                lawyer.burden()

                //律师代替小民进行辩护
                lawyer.defend()

                //完成诉讼
                lawyer.finish()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public class DynamicProxy: InvocationHandler {\n" +
                "        private lateinit var any: Any\n" +
                "        \n" +
                "        constructor(any: Any) {\n" +
                "            this.any = any\n" +
                "        }\n" +
                "\n" +
                "        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any {\n" +
                "            return method.invoke(any, args)\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public interface ILawsuit {\n" +
                "        //提交申请\n" +
                "        fun submit()\n" +
                "        \n" +
                "        //进行举证\n" +
                "        fun burden()\n" +
                "        \n" +
                "        //开始辩护\n" +
                "        fun defend()\n" +
                "        \n" +
                "        //诉讼完成\n" +
                "        fun finish()\n" +
                "    }\n" +
                "    \n" +
                "    public class XiaoMin: ILawsuit {\n" +
                "        override fun submit() {\n" +
                "            //老板欠小民工资，小民只好申请仲裁\n" +
                "            Log.d(\"XiaoMin\", \"老板拖欠工资！特此申请仲裁！\")\n" +
                "        }\n" +
                "\n" +
                "        override fun burden() {\n" +
                "            //小民证据充足，不怕告不赢\n" +
                "            Log.d(\"XiaoMin\", \"这是合同书和过去一年的银行工资流水！\")\n" +
                "        }\n" +
                "\n" +
                "        override fun defend() {\n" +
                "            //铁证如山，辩护也没什么好说的\n" +
                "            Log.d(\"XiaoMin\", \"证据确凿！不需要再说什么了！\")\n" +
                "        }\n" +
                "\n" +
                "        override fun finish() {\n" +
                "            //结果也是肯定的，必赢\n" +
                "            Log.d(\"XiaoMin\", \"诉讼成功！判决老板即日起七天内结算工资\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Client {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                //构造一个小民\n" +
                "                val xiaomin = XiaoMin()\n" +
                "                \n" +
                "                //构造一个动态代理\n" +
                "                val proxy = DynamicProxy(xiaomin)\n" +
                "                \n" +
                "                //获取被代理类小民的ClassLoader\n" +
                "                val loader = xiaomin.javaClass.classLoader\n" +
                "                \n" +
                "                //动态构造一个代理者律师\n" +
                "                val lawyer = Proxy.newProxyInstance(loader, arrayOf(ILawsuit::class.java), proxy) as ILawsuit\n" +
                "                \n" +
                "                //律师提交诉讼申请\n" +
                "                lawyer.submit()\n" +
                "                \n" +
                "                //律师进行举证\n" +
                "                lawyer.burden()\n" +
                "                \n" +
                "                //律师代替小民进行辩护\n" +
                "                lawyer.defend()\n" +
                "                \n" +
                "                //完成诉讼\n" +
                "                lawyer.finish()\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.proxy_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.proxy_disadvantages)
    }
}