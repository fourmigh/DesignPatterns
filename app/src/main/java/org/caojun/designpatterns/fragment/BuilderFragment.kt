package org.caojun.designpatterns.fragment

import android.app.AlertDialog
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
 * Builder模式
 */
class BuilderFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvBuilder.text = "        AlertDialog.Builder(context)\n" +
                "                .setIcon(android.R.drawable.ic_dialog_alert)\n" +
                "                .setTitle(\"Title\")\n" +
                "                .setMessage(\"Message\")\n" +
                "                .setPositiveButton(\"Positive\") { dialog, which -> Log.d(\"Positive\", \"onClick\") }\n" +
                "                .setNegativeButton(\"Negative\") { dialog, which -> Log.d(\"Negative\", \"onClick\") }\n" +
                "                .setNeutralButton(\"Neutral\") { dialog, which -> Log.d(\"Neutral\", \"onClick\") }\n" +
                "                .create().show()"

        tvBuilder.setOnClickListener {
            showDialog()
        }

        etvAdvantages.content = ArrayUtils.getString(context!!, R.array.builder_advantages)
        etvDisadvantages.content = ArrayUtils.getString(context!!, R.array.builder_disadvantages)
    }

    private fun showDialog() {
        AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("Positive") { dialog, which -> Log.d("Positive", "onClick") }
                .setNegativeButton("Negative") { dialog, which -> Log.d("Negative", "onClick") }
                .setNeutralButton("Neutral") { dialog, which -> Log.d("Neutral", "onClick") }
                .create().show()
    }
}