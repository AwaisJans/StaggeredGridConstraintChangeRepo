package com.example.android_project_staggered.dashboard.modelDashb

import com.google.gson.annotations.SerializedName


data class DashboardResponse(
    @SerializedName("dashboard")
    val dashboard: List<DashboardItem>
)