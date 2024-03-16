package com.example.android_project_staggered.dashboard.modelDashb

import com.google.gson.annotations.SerializedName

data class DashboardItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("navTitle")
    val navTitle: String,
    @SerializedName("block")
    val block: String,
    @SerializedName("microsite")
    val microsite: Boolean,
    @SerializedName("pushInternalKey")
    val pushInternalKey: String,
    @SerializedName("cellLayoutSameasModule")
    val cellLayoutSameAsModule: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tintcolor")
    val tintColor: String,
    @SerializedName("cellBackgroundColor")
    val cellBackgroundColor: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("imagename")
    val imageName: String,
    @SerializedName("submodules")
    val submodules: List<Submodule>
)