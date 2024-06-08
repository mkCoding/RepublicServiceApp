package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.network.api.ApiEndpoints
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.DriversModel
import com.kemakolam.republicserviceapp.data.network.model.RouteModel
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiEndpoints: ApiEndpoints) :
    ApiRepository {
    override suspend fun getDrivers(): List<DriverModel?>? = apiEndpoints.getDriversAndRoutes().drivers
    override suspend fun getRoutes(): List<RouteModel?>? = apiEndpoints.getDriversAndRoutes().routes


}