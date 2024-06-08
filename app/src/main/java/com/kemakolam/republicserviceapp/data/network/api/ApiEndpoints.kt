package com.kemakolam.republicserviceapp.data.network.api

import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.DriversModel
import retrofit2.http.GET

interface ApiEndpoints {

    //  /data - endpoint
    @GET(ApiDetails.DRIVERS_AND_ROUTES_ENDPOINT)
    suspend fun getDriversAndRoutes():  DriversModel
}