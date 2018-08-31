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
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * 享元模式
 */
class FlyweightFragment: Fragment() {

    public interface Ticket {
        public fun showTicketInfo(bunk: String)
    }

    /**
     * 火车票
     */
    public class TrainTicket: Ticket {
        public lateinit var from: String//始发地
        public lateinit var to: String//目的地
        public lateinit var bunk: String//铺位
        public var price: Int = 0//票价

        constructor(from: String, to: String) {
            this.from = from
            this.to = to
        }

        override fun showTicketInfo(bunk: String) {
            this.bunk = bunk
            this.price = Random().nextInt(300)
            Log.d("TrainTicket", "购买从" + from + "到" + to + "的" + bunk + "火车票，价格：" + price)
        }
    }

    public object TicketFactory {
        val sTicketMap = ConcurrentHashMap<String, Ticket>()

        fun getTicket(from: String, to: String): Ticket {
            val key = from + "-" + to
            return if (sTicketMap.containsKey(key)) {
                sTicketMap[key]!!
            } else {
                val ticket = TrainTicket(from, to)
                sTicketMap[key] = ticket
                ticket
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "    public interface Ticket {\n" +
                "        public fun showTicketInfo(bunk: String)\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 火车票\n" +
                "     */\n" +
                "    public class TrainTicket: Ticket {\n" +
                "        public lateinit var from: String//始发地\n" +
                "        public lateinit var to: String//目的地\n" +
                "        public lateinit var bunk: String//铺位\n" +
                "        public var price: Int = 0//票价\n" +
                "\n" +
                "        constructor(from: String, to: String) {\n" +
                "            this.from = from\n" +
                "            this.to = to\n" +
                "        }\n" +
                "\n" +
                "        override fun showTicketInfo(bunk: String) {\n" +
                "            this.bunk = bunk\n" +
                "            this.price = Random().nextInt(300)\n" +
                "            Log.d(\"TrainTicket\", \"购买从\" + from + \"到\" + to + \"的\" + bunk + \"火车票，价格：\" + price)\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public object TicketFactory {\n" +
                "        val sTicketMap = ConcurrentHashMap<String, Ticket>()\n" +
                "\n" +
                "        fun getTicket(from: String, to: String): Ticket {\n" +
                "            val key = from + \"-\" + to\n" +
                "            return if (sTicketMap.containsKey(key)) {\n" +
                "                sTicketMap[key]!!\n" +
                "            } else {\n" +
                "                val ticket = TrainTicket(from, to)\n" +
                "                sTicketMap[key] = ticket\n" +
                "                ticket\n" +
                "            }\n" +
                "        }\n" +
                "    }"

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.flyweight_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.flyweight_disadvantages)
    }
}