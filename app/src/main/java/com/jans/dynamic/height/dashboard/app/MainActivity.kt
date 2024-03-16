package com.jans.dynamic.height.dashboard.app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.android_project_staggered.dashboard.Util.config.Companion.setTitle1
import com.jans.dynamic.height.dashboard.app.dashboard.main.DashboardScreen


class MainActivity : AppCompatActivity() {


    private lateinit var btnDashboard:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       setTitle1(this)





        btnDashboard = findViewById(R.id.btnDashboard)


        btnDashboard.setOnClickListener{
            startActivity(Intent(this,DashboardScreen::class.java))
        }



    }

}