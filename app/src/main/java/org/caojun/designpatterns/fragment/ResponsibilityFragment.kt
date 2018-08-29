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
 * 责任链模式
 */
class ResponsibilityFragment: Fragment() {

    public abstract class Leader {
        public var nextHandler: Leader? = null

        /**
         * 自身能批复的额度权限
         * @return 额度
         */
        public abstract fun limit(): Int

        /**
         * 处理报账行为
         * @param money 具体金额
         */
        public abstract fun handle(money: Int)

        /**
         * 处理报账请求
         * @param money 能批复的报账额度
         */
        public final fun handleRequest(money: Int) {
            if (money < limit()) {
                handle(money)
            } else {
                nextHandler?.handleRequest(money)
            }
        }
    }

    public class GroupLeader: Leader() {
        override fun limit(): Int {
            return 1000
        }

        override fun handle(money: Int) {
            Log.d("GroupLeader", "组长批复报销" + money + "元")
        }
    }

    public class Director: Leader() {
        override fun limit(): Int {
            return 5000
        }

        override fun handle(money: Int) {
            Log.d("Director", "主管批复报销" + money + "元")
        }
    }

    public class Manager: Leader() {
        override fun limit(): Int {
            return 10000
        }

        override fun handle(money: Int) {
            Log.d("Manager", "经理批复报销" + money + "元")
        }
    }

    public class Boss: Leader() {
        override fun limit(): Int {
            return Int.MAX_VALUE
        }

        override fun handle(money: Int) {
            Log.d("Manager", "老板批复报销" + money + "元")
        }
    }

    public class XiaoMin {
        companion object {
            public fun main(args: Array<String>) {
                //构造各个领导对象
                val groupLeader = GroupLeader()
                val director = Director()
                val manager = Manager()
                val boss = Boss()
                //设置上一级领导处理者对象
                groupLeader.nextHandler = director
                director.nextHandler = manager
                manager.nextHandler = boss
                //发起报账申请
                groupLeader.handleRequest(50000)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public abstract class Leader {\n" +
                "        public var nextHandler: Leader? = null\n" +
                "\n" +
                "        /**\n" +
                "         * 自身能批复的额度权限\n" +
                "         * @return 额度\n" +
                "         */\n" +
                "        public abstract fun limit(): Int\n" +
                "\n" +
                "        /**\n" +
                "         * 处理报账行为\n" +
                "         * @param money 具体金额\n" +
                "         */\n" +
                "        public abstract fun handle(money: Int)\n" +
                "\n" +
                "        /**\n" +
                "         * 处理报账请求\n" +
                "         * @param money 能批复的报账额度\n" +
                "         */\n" +
                "        public final fun handleRequest(money: Int) {\n" +
                "            if (money < limit()) {\n" +
                "                handle(money)\n" +
                "            } else {\n" +
                "                nextHandler?.handleRequest(money)\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class GroupLeader: Leader() {\n" +
                "        override fun limit(): Int {\n" +
                "            return 1000\n" +
                "        }\n" +
                "\n" +
                "        override fun handle(money: Int) {\n" +
                "            Log.d(\"GroupLeader\", \"组长批复报销\" + money + \"元\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Director: Leader() {\n" +
                "        override fun limit(): Int {\n" +
                "            return 5000\n" +
                "        }\n" +
                "\n" +
                "        override fun handle(money: Int) {\n" +
                "            Log.d(\"Director\", \"主管批复报销\" + money + \"元\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Manager: Leader() {\n" +
                "        override fun limit(): Int {\n" +
                "            return 10000\n" +
                "        }\n" +
                "\n" +
                "        override fun handle(money: Int) {\n" +
                "            Log.d(\"Manager\", \"经理批复报销\" + money + \"元\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class Boss: Leader() {\n" +
                "        override fun limit(): Int {\n" +
                "            return Int.MAX_VALUE\n" +
                "        }\n" +
                "\n" +
                "        override fun handle(money: Int) {\n" +
                "            Log.d(\"Manager\", \"老板批复报销\" + money + \"元\")\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public class XiaoMin {\n" +
                "        companion object {\n" +
                "            public fun main(args: Array<String>) {\n" +
                "                //构造各个领导对象\n" +
                "                val groupLeader = GroupLeader()\n" +
                "                val director = Director()\n" +
                "                val manager = Manager()\n" +
                "                val boss = Boss()\n" +
                "                //设置上一级领导处理者对象\n" +
                "                groupLeader.nextHandler = director\n" +
                "                director.nextHandler = manager\n" +
                "                manager.nextHandler = boss\n" +
                "                //发起报账申请\n" +
                "                groupLeader.handleRequest(50000)\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.responsibility_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.responsibility_disadvantages)
    }
}