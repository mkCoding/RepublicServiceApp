package com.kemakolam.republicserviceapp.data.network.model


import com.google.gson.annotations.SerializedName

data class DriverModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = ""
)