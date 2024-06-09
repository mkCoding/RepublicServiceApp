package com.kemakolam.republicserviceapp.data.repository

import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.data.network.api.ApiEndpoints
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.RouteModel
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import javax.inject.Inject

import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity

class ApiRepositoryImpl @Inject constructor(
    private val apiEndpoints: ApiEndpoints,
    private val driversDao: DriversDao,
    private val routeDao:RoutesDao

) :
    ApiRepository {
    override suspend fun getDrivers(): List<DriverModel?>? = apiEndpoints.getDriversAndRoutes().drivers
    override suspend fun getRoutes(): List<RouteModel?>? = apiEndpoints.getDriversAndRoutes().routes


//-------------This code below ONLY! fetches an stores Drivers and Routes to DB-----------------
    override suspend fun fetchAndStoreDrivers() {
        val drivers = getDrivers() //make the api call to get Drivers
        val driverEntities = drivers?.map { DriverEntity(id = it?.id, name = it?.name) }
//        driverEntities?.let { driversDao.insertDrivers(it) } //add the drivers list retrieved from API call to DB

    //do not add duplicate drivers to DB
    driverEntities?.forEach{driver ->
        val existingDriver = driversDao.getDriverById(driver.id?:"")
        if(existingDriver == null){
            driversDao.insertDrivers(listOf(driver))
        }

    }


    }

    override suspend fun fetchAndStoreRoutes() {
        val routes = getRoutes() //make api call and get Routes
        val routeEntities = routes?.map { RouteEntity(id = it?.id, name = it?.name, type = it?.type) }
        routeEntities?.let { routeDao.insertRoutes(it) } //add the routes list retrieved from API call to DB
    }

    //-------------------Returns drivers and routes list specifically from the DB---------------
    override suspend fun getStoredDrivers(): List<DriverEntity> {
        return driversDao.getAllDrivers()
    }

    override suspend fun getStoredRoutes(): List<RouteEntity> {
        return routeDao.getAllRoutes()
    }


}