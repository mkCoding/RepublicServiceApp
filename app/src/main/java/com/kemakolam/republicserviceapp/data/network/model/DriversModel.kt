package com.kemakolam.republicserviceapp.data.network.model


import com.google.gson.annotations.SerializedName

data class DriversModel(
    @SerializedName("drivers")
    val drivers: List<DriverModel?>? = listOf(),
    @SerializedName("routes")
    val routes: List<RouteModel?>? = listOf()
)