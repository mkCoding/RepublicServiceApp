package com.example.republicserviceapp.data.repository

import com.example.republicserviceapp.data.network.api.ApiEndpoints
import com.example.republicserviceapp.data.network.model.DriverModel
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiEndpoints: ApiEndpoints) :ApiRepository {
    override suspend fun getDrivers(): DriverModel = apiEndpoints.getDrivers()
}