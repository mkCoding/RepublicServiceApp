package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.network.api.ApiEndpoints
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiEndpoints: ApiEndpoints) :
    ApiRepository {
    override suspend fun getDrivers(): DriverModel = apiEndpoints.getDriversAndRoutes()
}