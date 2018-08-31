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
 * 桥接模式
 */
class BridgeFragment: Fragment() {

    public abstract class CoffeeAdditives {
        /**
         * 具体要往咖啡里添加什么也是由子类实现
         * @return 具体添加的东西
         */
        public abstract fun addSomething(): String
    }

    public abstract class Coffee {
        public lateinit var impl: CoffeeAdditives

        constructor(impl: CoffeeAdditives) {
            this.impl = impl
        }

        /**
         * 咖啡具体是什么样的由子类决定
         */
        public abstract fun makeCoffee()
    }

    public class LargeCoffee: Coffee {
        constructor(impl: CoffeeAdditives): super(impl)

        override fun makeCoffee() {
            Log.d("LargeCoffee", "大杯的" + impl + "咖啡")
        }
    }

    public class SmallCoffee: Coffee {
        constructor(impl: CoffeeAdditives): super(impl)

        override fun makeCoffee() {
            Log.d("SmallCoffee", "小杯的" + impl + "咖啡")
        }
    }

    public class Sugar: CoffeeAdditives() {
        override fun addSomething(): String {
            return "加糖"
        }
    }

    public class Ordinary: CoffeeAdditives() {
        override fun addSomething(): String {
            return "原味"
        }
    }

    public class Client {
        companion object {
            public fun main(args: Array<String>) {
                //原汁原味
                val implOrdinary = Ordinary()

                //准备焦糖
                val implSugar = Sugar()

                //大杯咖啡，原味
                val largeCoffeeOrdinary = LargeCoffee(implOrdinary)
                largeCoffeeOrdinary.makeCoffee()

                //小杯咖啡，加糖
                val smallCoffeeSugar = SmallCoffee(implSugar)
                smallCoffeeSugar.makeCoffee()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public abstract class CoffeeAdditives {\n" +
                "        /**\n" +
                "         * 具体要往咖啡里添加什么也是由子类实现\n" +
                "         * @return 具体添加的东西\n" +
                "         */\n" +
                "        public abstract fun addSomething(): String\n" +
                "    }\n" +
                "    \n" +
                "    public abstract class Coffee {\n" +
                "        public lateinit var impl: CoffeeAdditives\n" +
                "        \n" +
                "        constructor(impl: CoffeeAdditives) {\n" +
                "            this.impl = impl\n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * 咖啡具体是什么样的由子类决定\n" +
                "         */\n" +
                "        public abstract fun makeCoffee()\n" +
                "    }\n" +
                "    \n" +
                "    public class LargeCoffee: Coffee {\n" +
                "        constructor(impl: CoffeeAdditives): super(impl)\n" +
                "\n" +
                "        override fun makeCoffee() {\n" +
                "            Log.d(\"LargeCoffee\", \"大杯的\" + impl + \"咖啡\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class SmallCoffee: Coffee {\n" +
                "        constructor(impl: CoffeeAdditives): super(impl)\n" +
                "\n" +
                "        override fun makeCoffee() {\n" +
                "            Log.d(\"SmallCoffee\", \"小杯的\" + impl + \"咖啡\")\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class Sugar: CoffeeAdditives() {\n" +
                "        override fun addSomething(): String {\n" +
                "            return \"加糖\"\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Ordinary: CoffeeAdditives() {\n" +
                "        override fun addSomething(): String {\n" +
                "            return \"原味\"\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Client {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                //原汁原味\n" +
                "                val implOrdinary = Ordinary()\n" +
                "                \n" +
                "                //准备焦糖\n" +
                "                val implSugar = Sugar()\n" +
                "                \n" +
                "                //大杯咖啡，原味\n" +
                "                val largeCoffeeOrdinary = LargeCoffee(implOrdinary)\n" +
                "                largeCoffeeOrdinary.makeCoffee()\n" +
                "                \n" +
                "                //小杯咖啡，加糖\n" +
                "                val smallCoffeeSugar = SmallCoffee(implSugar)\n" +
                "                smallCoffeeSugar.makeCoffee()\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.bridge_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.bridge_disadvantages)
    }
}