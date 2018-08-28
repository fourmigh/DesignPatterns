package org.caojun.designpatterns.utils

import android.content.Context

object ArrayUtils {

    fun getString(context: Context, arrayResId: Int): String {
        val array = context.resources.getStringArray(arrayResId)
        val sb = StringBuilder()
        for (i in 0 until array.size) {
            sb.append(array[i])
            if (i < array.size - 1) {
                sb.append("\n")
            }
        }
        return sb.toString()
    }
}