package com.jans.dynamic.height.dashboard.app.dashboard.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_project_staggered.dashboard.Util.config
import com.jans.dynamic.height.dashboard.app.dashboard.adapter.dashboardAdapter
import com.example.android_project_staggered.dashboard.modelDashb.DashboardItem
import com.example.android_project_staggered.dashboard.modelDashb.DashboardResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.jans.dynamic.height.dashboard.app.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class DashboardScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_screen)


        config.setTitle1(this)


        val jsonString = readJsonFile(R.raw.dashboard_modules)
        val dashboardResponse = Gson().fromJson(jsonString, DashboardResponse::class.java)
        val dashboardItems: List<DashboardItem> = dashboardResponse.dashboard

        staggeredRV(dashboardItems)

        val nestedScrollView: NestedScrollView = findViewById(R.id.nestedScrollView)

        nestedScrollView.visibility = View.INVISIBLE

        val rvProgressBar = findViewById<LinearLayout>(R.id.rvProgressBar)
        Handler(Looper.getMainLooper()).postDelayed({
            nestedScrollView.visibility = View.VISIBLE
            rvProgressBar.visibility = GONE
        }, 50) // Delay in milliseconds


        val btnBottom = findViewById<FloatingActionButton>(R.id.btnBottom)

        btnBottom.setOnClickListener {
            nestedScrollView.fullScroll(View.FOCUS_DOWN)
        }
    }


    private fun staggeredRV(dashboardItem: List<DashboardItem>) {
        val adapter = dashboardAdapter(dashboardItem)
        val dashboardRV = findViewById<RecyclerView>(R.id.verticalRecyclerView)
        val verticalLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        dashboardRV.layoutManager = verticalLayoutManager
        dashboardRV.adapter = adapter
    }
    fun Context.readJsonFile(resourceId: Int): String {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return buildString {
            try {
                var line = reader.readLine()
                while (line != null) {
                    append(line).append('\n')
                    line = reader.readLine()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    reader.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}


