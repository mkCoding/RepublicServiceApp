package com.kemakolam.republicserviceapp.ui.driver

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import com.kemakolam.republicserviceapp.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DriverViewModel @Inject constructor(private val repository:ApiRepository, private var driversDao: DriversDao, private var routesDao: RoutesDao):ViewModel(){

    //If json response starts with an Object use this -> MutableLiveData<Model>
    //If json response starts with an Array use this -> MutableLiveData<ArrayList<Model>>

    //API Structure
//    private val _driversList  = MutableLiveData<List<DriverModel?>?>()
//    val driversList: LiveData<List<DriverModel?>?> = _driversList

    //DB Structure
    val _driversList = MutableLiveData<List<DriverEntity>?>()
    val driversList: LiveData<List<DriverEntity>?> = _driversList

    init {
        getAllDrivers()
    }

//    suspend fun getDriversDao():List<DriverEntity?>?{
//        return repository.getStoredDrivers();
//    }

    //Used in AppNavHost instead of injecting dependencies in MainActivity
    suspend fun getRoutesDao():List<RouteEntity>{
        return repository.getStoredRoutes();
    }

//    suspend fun getDrivers(){
//
//    }
//
//    suspend fun getRoutes(){
//
//    }

     fun getAllDrivers() {

        viewModelScope.launch {

            //get the Drivers list specifically from the DB
            val allDrivers = repository.getStoredDrivers()
            val allRoutes = repository.getStoredRoutes()

            //if there are no drivers in the DB then fetch drivers from API and put them in the DB
            if(allDrivers == null && allRoutes == null || allRoutes!=null && allRoutes==null ||allDrivers==null && allRoutes!=null ){
                try{
                    repository.fetchAndStoreDrivers() //fetch and store driver to DB
                    repository.fetchAndStoreRoutes() //fetch and store routes to DB
                }catch (e:Exception){
                    // Handle error fetching data from API or storing data in the DB
                    Log.e("DriverViewModel", "Error fetching or storing data: ${e.message}")
                    return@launch
                }

            }

            //if there are drivers in the DB
            if(allDrivers!=null){

                //print drivers to the console
                Log.d("DriverViewModel", allDrivers.toString())

                //expose data to the view
                _driversList.postValue(allDrivers)
            }
        }
    }

}