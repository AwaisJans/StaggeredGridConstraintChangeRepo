package com.example.android_project_staggered.dashboard.Util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.jans.dynamic.height.dashboard.app.R

class config {
    companion object {



            fun setTitle1(activity: Context){
                    activity as AppCompatActivity
                    val actionBar: ActionBar = activity.supportActionBar!!
                    val colorDrawable = ColorDrawable(Color.parseColor("#000000"))
                    actionBar.setBackgroundDrawable(colorDrawable)
                    actionBar.title = Html.fromHtml("<font color=#FFFFFF>" + activity.getString(R.string.app_name) + "</font>");
            }



//        values for all blocks
        const val ITEM_R1 = 1
        const val ITEM_R2 = 2
        const val ITEM_R3 =  3
        const val ITEM_S1 =  4
        const val ITEM_S2 =  5
        const val ITEM_RTF =  6
        const val ITEM_RTH =  7
        const val ITEM_RT =  8
        var tvWidth: Int = 0

        // margins for RT View Holder
        const val RT_MARGIN_TOP = 60
        const val RT_MARGIN_START = 60
        const val RT_MARGIN_END = 60



    }
}