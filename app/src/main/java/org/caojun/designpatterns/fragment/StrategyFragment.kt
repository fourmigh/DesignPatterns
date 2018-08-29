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
 * 策略模式
 */
class StrategyFragment: Fragment() {

    /**
     * 计算接口
     */
    public interface CalculateStrategy {
        /**
         * 按距离来计算价格
         * @param km 公里
         * @return 返回价格
         */
        fun calculatePrice(km: Int): Int
    }

    /**
     * 公交车价格计算策略
     */
    public class BusStrategy: CalculateStrategy {
        /**
         * 北京公交车，十公里之内一元，超过十公里之后每加一元钱可以乘5公里
         * @param km 公里
         * @return 价格
         */
        override fun calculatePrice(km: Int): Int {
            //超过10公里的总距离
            val extraTotal = km - 10
            //超过的距离是5公里的倍数
            val extraFactor = extraTotal / 5
            //超过的距离对5公里取余
            val fraction = extraTotal % 5
            //计算价格
            var price = 1 + extraFactor * 1
            return if (fraction > 0) ++price else price
        }
    }

    /**
     * 地铁价格计算策略
     */
    public class SubwayStrategy: CalculateStrategy {
        /**
         * 6公里（含）内3元；6~12公里（含）4元；12~22公里（含）5元；22~32公里（含）6元
         * @param km 公里
         * @return 价格
         */
        override fun calculatePrice(km: Int): Int {
            return when {
                km <= 6 -> 3
                km <= 12 -> 4
                km <= 22 -> 5
                km <= 32 -> 6
                else -> 7
            }
        }
    }
    /**
     * 出租车计算策略
     */
    public class TaxiStrategy: CalculateStrategy {
        /**
         * 公里数*2
         * @param km 公里
         * @return 价格
         */
        override fun calculatePrice(km: Int): Int {
            return km * 2
        }
    }
    /**
     * 公交出行价格计算器
     */
    public class TranficCalculator {
        lateinit var mStrategy: CalculateStrategy
        public fun calculatePrice(km: Int): Int {
            return mStrategy.calculatePrice(km)
        }
        companion object {
            public fun main(args: Array<String>) {
                val calculator = TranficCalculator()
                //设置计算策略
                calculator.mStrategy = BusStrategy()
                Log.d("TranficCalculator", "公交车乘16公里的价格： " + calculator.calculatePrice(16))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    /**\n" +
                "     * 计算接口\n" +
                "     */\n" +
                "    public interface CalculateStrategy {\n" +
                "        /**\n" +
                "         * 按距离来计算价格\n" +
                "         * @param km 公里\n" +
                "         * @return 返回价格\n" +
                "         */\n" +
                "        fun calculatePrice(km: Int): Int\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 公交车价格计算策略\n" +
                "     */\n" +
                "    public class BusStrategy: CalculateStrategy {\n" +
                "        /**\n" +
                "         * 北京公交车，十公里之内一元，超过十公里之后每加一元钱可以乘5公里\n" +
                "         * @param km 公里\n" +
                "         * @return 价格\n" +
                "         */\n" +
                "        override fun calculatePrice(km: Int): Int {\n" +
                "            //超过10公里的总距离\n" +
                "            val extraTotal = km - 10\n" +
                "            //超过的距离是5公里的倍数\n" +
                "            val extraFactor = extraTotal / 5\n" +
                "            //超过的距离对5公里取余\n" +
                "            val fraction = extraTotal % 5\n" +
                "            //计算价格\n" +
                "            var price = 1 + extraFactor * 1\n" +
                "            return if (fraction > 0) ++price else price\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 地铁价格计算策略\n" +
                "     */\n" +
                "    public class SubwayStrategy: CalculateStrategy {\n" +
                "        /**\n" +
                "         * 6公里（含）内3元；6~12公里（含）4元；12~22公里（含）5元；22~32公里（含）6元\n" +
                "         * @param km 公里\n" +
                "         * @return 价格\n" +
                "         */\n" +
                "        override fun calculatePrice(km: Int): Int {\n" +
                "            return when {\n" +
                "                km <= 6 -> 3\n" +
                "                km <= 12 -> 4\n" +
                "                km <= 22 -> 5\n" +
                "                km <= 32 -> 6\n" +
                "                else -> 7\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    /**\n" +
                "     * 出租车计算策略\n" +
                "     */\n" +
                "    public class TaxiStrategy: CalculateStrategy {\n" +
                "        /**\n" +
                "         * 公里数*2\n" +
                "         * @param km 公里\n" +
                "         * @return 价格\n" +
                "         */\n" +
                "        override fun calculatePrice(km: Int): Int {\n" +
                "            return km * 2\n" +
                "        }\n" +
                "    }\n" +
                "    /**\n" +
                "     * 公交出行价格计算器\n" +
                "     */\n" +
                "    public class TranficCalculator {\n" +
                "        lateinit var mStrategy: CalculateStrategy\n" +
                "        public fun calculatePrice(km: Int): Int {\n" +
                "            return mStrategy.calculatePrice(km)\n" +
                "        }\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                val calculator = TranficCalculator()\n" +
                "                //设置计算策略\n" +
                "                calculator.mStrategy = BusStrategy()\n" +
                "                Log.d(\"TranficCalculator\", \"公交车乘16公里的价格： \" + calculator.calculatePrice(16))\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.strategy_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.strategy_disadvantages)
    }
}