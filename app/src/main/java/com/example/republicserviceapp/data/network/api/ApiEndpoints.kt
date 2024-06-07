package com.example.republicserviceapp.data.network.api

import com.example.republicserviceapp.data.network.model.DriverModel
import retrofit2.http.GET

interface ApiEndpoints {

    //  /data - endpoint
    @GET(ApiDetails.DRIVERS_AND_ROUTES_ENDPOINT)
    suspend fun getDrivers():DriverModel
}