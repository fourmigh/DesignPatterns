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
 * 抽象工厂模式
 */
class AbstractFactoryFragment: Fragment() {

    public interface ITire {
        /**
         * 轮胎
         */
        fun tire()
    }

    public interface IEngine {
        /**
         * 发动机
         */
        fun engine()
    }

    public interface IBrake {
        /**
         * 制动系统
         */
        fun brake()
    }

    public abstract class CarFactory {
        /**
         * 生产轮胎
         * @return 轮胎
         */
        public abstract fun createTire(): ITire

        /**
         * 生产发动机
         * @return 发动机
         */
        public abstract fun createEngine(): IEngine

        /**
         * 生产制动系统
         * @return 制动系统
         */
        public abstract fun createBrake(): IBrake
    }

    public class NormalTire: ITire {
        override fun tire() {
            Log.d("NormalTire", "普通轮胎")
        }
    }

    public class SUVTire: ITire {
        override fun tire() {
            Log.d("SUVTire", "越野轮胎")
        }
    }

    public class DomesticEngine: IEngine {
        override fun engine() {
            Log.d("DomesticEngine", "国产发动机")
        }
    }

    public class ImportEngine: IEngine {
        override fun engine() {
            Log.d("ImportEngine", "进口发动机")
        }
    }

    public class NormalBrake: IBrake {
        override fun brake() {
            Log.d("NormalBrake", "普通制动")
        }
    }

    public class SeniorBrake: IBrake {
        override fun brake() {
            Log.d("SeniorBrake", "高级制动")
        }
    }

    public class Q3Factory: CarFactory() {
        override fun createTire(): ITire {
            return NormalTire()
        }

        override fun createEngine(): IEngine {
            return DomesticEngine()
        }

        override fun createBrake(): IBrake {
            return NormalBrake()
        }
    }

    public class Q7Factory: CarFactory() {
        override fun createTire(): ITire {
            return SUVTire()
        }

        override fun createEngine(): IEngine {
            return ImportEngine()
        }

        override fun createBrake(): IBrake {
            return SeniorBrake()
        }
    }

    public class Client {
        companion object {
            public fun main(args: Array<String>) {
                //构造一个生产Q3的工厂
                val factoryQ3 = Q3Factory()
                factoryQ3.createTire().tire()
                factoryQ3.createEngine().engine()
                factoryQ3.createBrake().brake()

                //构造一个生产Q7的工厂
                val factoryQ7 = Q3Factory()
                factoryQ7.createTire().tire()
                factoryQ7.createEngine().engine()
                factoryQ7.createBrake().brake()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public interface ITire {\n" +
                "        /**\n" +
                "         * 轮胎\n" +
                "         */\n" +
                "        fun tire()\n" +
                "    }\n" +
                "\n" +
                "    public interface IEngine {\n" +
                "        /**\n" +
                "         * 发动机\n" +
                "         */\n" +
                "        fun engine()\n" +
                "    }\n" +
                "\n" +
                "    public interface IBrake {\n" +
                "        /**\n" +
                "         * 制动系统\n" +
                "         */\n" +
                "        fun brake()\n" +
                "    }\n" +
                "\n" +
                "    public abstract class CarFactory {\n" +
                "        /**\n" +
                "         * 生产轮胎\n" +
                "         * @return 轮胎\n" +
                "         */\n" +
                "        public abstract fun createTire(): ITire\n" +
                "\n" +
                "        /**\n" +
                "         * 生产发动机\n" +
                "         * @return 发动机\n" +
                "         */\n" +
                "        public abstract fun createEngine(): IEngine\n" +
                "\n" +
                "        /**\n" +
                "         * 生产制动系统\n" +
                "         * @return 制动系统\n" +
                "         */\n" +
                "        public abstract fun createBrake(): IBrake\n" +
                "    }\n" +
                "\n" +
                "    public class NormalTire: ITire {\n" +
                "        override fun tire() {\n" +
                "            Log.d(\"NormalTire\", \"普通轮胎\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class SUVTire: ITire {\n" +
                "        override fun tire() {\n" +
                "            Log.d(\"SUVTire\", \"越野轮胎\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class DomesticEngine: IEngine {\n" +
                "        override fun engine() {\n" +
                "            Log.d(\"DomesticEngine\", \"国产发动机\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class ImportEngine: IEngine {\n" +
                "        override fun engine() {\n" +
                "            Log.d(\"ImportEngine\", \"进口发动机\")\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class NormalBrake: IBrake {\n" +
                "        override fun brake() {\n" +
                "            Log.d(\"NormalBrake\", \"普通制动\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class SeniorBrake: IBrake {\n" +
                "        override fun brake() {\n" +
                "            Log.d(\"SeniorBrake\", \"高级制动\")\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class Q3Factory: CarFactory() {\n" +
                "        override fun createTire(): ITire {\n" +
                "            return NormalTire()\n" +
                "        }\n" +
                "\n" +
                "        override fun createEngine(): IEngine {\n" +
                "            return DomesticEngine()\n" +
                "        }\n" +
                "\n" +
                "        override fun createBrake(): IBrake {\n" +
                "            return NormalBrake()\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Q7Factory: CarFactory() {\n" +
                "        override fun createTire(): ITire {\n" +
                "            return SUVTire()\n" +
                "        }\n" +
                "\n" +
                "        override fun createEngine(): IEngine {\n" +
                "            return ImportEngine()\n" +
                "        }\n" +
                "\n" +
                "        override fun createBrake(): IBrake {\n" +
                "            return SeniorBrake()\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public class Client {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                //构造一个生产Q3的工厂\n" +
                "                val factoryQ3 = Q3Factory()\n" +
                "                factoryQ3.createTire().tire()\n" +
                "                factoryQ3.createEngine().engine()\n" +
                "                factoryQ3.createBrake().brake()\n" +
                "\n" +
                "                //构造一个生产Q7的工厂\n" +
                "                val factoryQ7 = Q3Factory()\n" +
                "                factoryQ7.createTire().tire()\n" +
                "                factoryQ7.createEngine().engine()\n" +
                "                factoryQ7.createBrake().brake()\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.abstract_factory_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.abstract_factory_disadvantages)
    }
}