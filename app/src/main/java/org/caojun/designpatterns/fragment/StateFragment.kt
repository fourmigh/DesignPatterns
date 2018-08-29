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
 * 状态模式
 */
class StateFragment: Fragment() {

    /**
     * 电视状态接口，定义了电视操作的函数
     */
    public interface TvState {
        public fun nextChannel()
        public fun prevChannel()
        public fun volumeUp()
        public fun volumeDown()
    }
    /**
     * 关机状态，此时只有开机功能是有效的
     */
    public class PowerOffState: TvState {
        override fun nextChannel() {
        }

        override fun prevChannel() {
        }

        override fun volumeUp() {
        }

        override fun volumeDown() {
        }
    }
    /**
     * 开机状态，此时再触发开机功能不做任何操作
     */
    public class PowerOnState: TvState {
        override fun nextChannel() {
            Log.d("PowerOnState", "下一个频道")
        }

        override fun prevChannel() {
            Log.d("PowerOnState", "上一个频道")
        }

        override fun volumeUp() {
            Log.d("PowerOnState", "调高音量")
        }

        override fun volumeDown() {
            Log.d("PowerOnState", "调低音量")
        }
    }
    /**
     * 电源操作接口
     */
    public interface PowerController {
        public fun powerOn()
        public fun powerOff()
    }
    /**
     * 电视遥控器，类似于经典状态模式中的Context
     */
    public class TvController: PowerController {
        lateinit var mTvState: TvState

        override fun powerOn() {
            mTvState = PowerOnState()
            Log.d("TvController", "开机啦")
        }

        override fun powerOff() {
            mTvState = PowerOffState()
            Log.d("TvController", "关机啦")
        }

        fun nextChannel() {
            mTvState.nextChannel()
        }

        fun prevChannel() {
            mTvState.prevChannel()
        }

        fun volumeUp() {
            mTvState.volumeUp()
        }

        fun volumeDown() {
            mTvState.volumeDown()
        }
    }

    /**
     * 客户端的调用代码
     */
    public class Client {
        companion object {
            public fun main(args: Array<String>) {
                val tvController = TvController()
                //设置开机状态
                tvController.powerOn()
                //下一个频道
                tvController.nextChannel()
                //调高音量
                tvController.volumeUp()
                //设置关机状态
                tvController.powerOff()
                //调高音量，此时不会生效
                tvController.volumeUp()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    /**\n" +
                "     * 电视状态接口，定义了电视操作的函数\n" +
                "     */\n" +
                "    public interface TvState {\n" +
                "        public fun nextChannel()\n" +
                "        public fun prevChannel()\n" +
                "        public fun volumeUp()\n" +
                "        public fun volumeDown()\n" +
                "    }\n" +
                "    /**\n" +
                "     * 关机状态，此时只有开机功能是有效的\n" +
                "     */\n" +
                "    public class PowerOffState: TvState {\n" +
                "        override fun nextChannel() {            \n" +
                "        }\n" +
                "\n" +
                "        override fun prevChannel() {\n" +
                "        }\n" +
                "\n" +
                "        override fun volumeUp() {\n" +
                "        }\n" +
                "\n" +
                "        override fun volumeDown() {\n" +
                "        }\n" +
                "    }\n" +
                "    /**\n" +
                "     * 开机状态，此时再触发开机功能不做任何操作\n" +
                "     */\n" +
                "    public class PowerOnState: TvState {\n" +
                "        override fun nextChannel() {\n" +
                "            Log.d(\"PowerOnState\", \"下一个频道\")\n" +
                "        }\n" +
                "\n" +
                "        override fun prevChannel() {\n" +
                "            Log.d(\"PowerOnState\", \"上一个频道\")\n" +
                "        }\n" +
                "\n" +
                "        override fun volumeUp() {\n" +
                "            Log.d(\"PowerOnState\", \"调高音量\")\n" +
                "        }\n" +
                "\n" +
                "        override fun volumeDown() {\n" +
                "            Log.d(\"PowerOnState\", \"调低音量\")\n" +
                "        }\n" +
                "    }\n" +
                "    /**\n" +
                "     * 电源操作接口\n" +
                "     */\n" +
                "    public interface PowerController {\n" +
                "        public fun powerOn()\n" +
                "        public fun powerOff()\n" +
                "    }\n" +
                "    /**\n" +
                "     * 电视遥控器，类似于经典状态模式中的Context\n" +
                "     */\n" +
                "    public class TvController: PowerController {\n" +
                "        lateinit var mTvState: TvState\n" +
                "\n" +
                "        override fun powerOn() {\n" +
                "            mTvState = PowerOnState()\n" +
                "            Log.d(\"TvController\", \"开机啦\")\n" +
                "        }\n" +
                "\n" +
                "        override fun powerOff() {\n" +
                "            mTvState = PowerOffState()\n" +
                "            Log.d(\"TvController\", \"关机啦\")\n" +
                "        }\n" +
                "\n" +
                "        fun nextChannel() {\n" +
                "            mTvState.nextChannel()\n" +
                "        }\n" +
                "\n" +
                "        fun prevChannel() {\n" +
                "            mTvState.prevChannel()\n" +
                "        }\n" +
                "\n" +
                "        fun volumeUp() {\n" +
                "            mTvState.volumeUp()\n" +
                "        }\n" +
                "\n" +
                "        fun volumeDown() {\n" +
                "            mTvState.volumeDown()\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 客户端的调用代码\n" +
                "     */\n" +
                "    public class Client {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                val tvController = TvController()\n" +
                "                //设置开机状态\n" +
                "                tvController.powerOn()\n" +
                "                //下一个频道\n" +
                "                tvController.nextChannel()\n" +
                "                //调高音量\n" +
                "                tvController.volumeUp()\n" +
                "                //设置关机状态\n" +
                "                tvController.powerOff()\n" +
                "                //调高音量，此时不会生效\n" +
                "                tvController.volumeUp()\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.state_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.state_disadvantages)
    }
}