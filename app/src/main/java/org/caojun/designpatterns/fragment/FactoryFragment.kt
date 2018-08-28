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
 * 工厂方法模式
 */
class FactoryFragment: Fragment() {

    /**
     * 汽车的抽象产品类
     */
    public abstract class AudiCar {

        /**
         * 定义汽车的一个行为方法，车可以启动开走
         */
        public abstract fun drive()

        /**
         * 定义汽车的一个行为方法，车可以自动巡航
         */
        public abstract fun selfNavigation()
    }

    /**
     * 工厂
     */
    public abstract class AudiFactory {

        /**
         * 某车型的工厂方法
         * @param clz 具体的型号
         * @param <T> 具体型号的车对象
         * @return
        </T> */
        public abstract fun <T : AudiCar> creeateAudiCar(clz: Class<T>): T
    }

    public class AudiCarFactory: AudiFactory() {

        override fun <T : AudiCar> creeateAudiCar(clz: Class<T>): T {
            val car = Class.forName(clz.name).newInstance()
            return car as T
        }
    }

    public class AudiQ3: AudiCar() {

        override fun drive() {
            Log.d("AudiQ3", "启动了")
        }

        override fun selfNavigation() {
            Log.d("AudiQ3", "开始自动巡航")
        }
    }

    public class AudiQ5: AudiCar() {

        override fun drive() {
            Log.d("AudiQ5", "启动了")
        }

        override fun selfNavigation() {
            Log.d("AudiQ5", "开始自动巡航")
        }
    }

    public class AudiQ7: AudiCar() {

        override fun drive() {
            Log.d("AudiQ7", "启动了")
        }

        override fun selfNavigation() {
            Log.d("AudiQ7", "开始自动巡航")
        }
    }

    public class Client {
        companion object {
            public fun main(args: Array<String>) {
                //构造一个制造汽车的工厂对象
                val factory = AudiCarFactory()

                //生产Q3并启动
                val audiQ3 = factory.creeateAudiCar(AudiQ3::class.java)
                audiQ3.drive()
                audiQ3.selfNavigation()

                //生产Q5并启动
                val audiQ5 = factory.creeateAudiCar(AudiQ5::class.java)
                audiQ5.drive()
                audiQ5.selfNavigation()

                //生产Q7并启动
                val audiQ7 = factory.creeateAudiCar(AudiQ7::class.java)
                audiQ7.drive()
                audiQ7.selfNavigation()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    /**\n" +
                "     * 汽车的抽象产品类\n" +
                "     */\n" +
                "    public abstract class AudiCar {\n" +
                "\n" +
                "        /**\n" +
                "         * 定义汽车的一个行为方法，车可以启动开走\n" +
                "         */\n" +
                "        public abstract fun drive()\n" +
                "\n" +
                "        /**\n" +
                "         * 定义汽车的一个行为方法，车可以自动巡航\n" +
                "         */\n" +
                "        public abstract fun selfNavigation()\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 工厂\n" +
                "     */\n" +
                "    public abstract class AudiFactory {\n" +
                "\n" +
                "        /**\n" +
                "         * 某车型的工厂方法\n" +
                "         * @param clz 具体的型号\n" +
                "         * @param <T> 具体型号的车对象\n" +
                "         * @return\n" +
                "        </T> */\n" +
                "        public abstract fun <T : AudiCar> creeateAudiCar(clz: Class<T>): T\n" +
                "    }\n" +
                "    \n" +
                "    public class AudiCarFactory: AudiFactory() {\n" +
                "        \n" +
                "        override fun <T : AudiCar> creeateAudiCar(clz: Class<T>): T {\n" +
                "            val car = Class.forName(clz.name).newInstance()\n" +
                "            return car as T\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class AudiQ3: AudiCar() {\n" +
                "        \n" +
                "        override fun drive() {\n" +
                "            Log.d(\"AudiQ3\", \"启动了\")\n" +
                "        }\n" +
                "\n" +
                "        override fun selfNavigation() {\n" +
                "            Log.d(\"AudiQ3\", \"开始自动巡航\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class AudiQ5: AudiCar() {\n" +
                "\n" +
                "        override fun drive() {\n" +
                "            Log.d(\"AudiQ5\", \"启动了\")\n" +
                "        }\n" +
                "\n" +
                "        override fun selfNavigation() {\n" +
                "            Log.d(\"AudiQ5\", \"开始自动巡航\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class AudiQ7: AudiCar() {\n" +
                "\n" +
                "        override fun drive() {\n" +
                "            Log.d(\"AudiQ7\", \"启动了\")\n" +
                "        }\n" +
                "\n" +
                "        override fun selfNavigation() {\n" +
                "            Log.d(\"AudiQ7\", \"开始自动巡航\")\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class Client {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                //构造一个制造汽车的工厂对象\n" +
                "                val factory = AudiCarFactory()\n" +
                "                \n" +
                "                //生产Q3并启动\n" +
                "                val audiQ3 = factory.creeateAudiCar(AudiQ3::class.java)\n" +
                "                audiQ3.drive()\n" +
                "                audiQ3.selfNavigation()\n" +
                "\n" +
                "                //生产Q5并启动\n" +
                "                val audiQ5 = factory.creeateAudiCar(AudiQ5::class.java)\n" +
                "                audiQ5.drive()\n" +
                "                audiQ5.selfNavigation()\n" +
                "\n" +
                "                //生产Q7并启动\n" +
                "                val audiQ7 = factory.creeateAudiCar(AudiQ7::class.java)\n" +
                "                audiQ7.drive()\n" +
                "                audiQ7.selfNavigation()\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.factory_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.factory_disadvantages)
    }
}