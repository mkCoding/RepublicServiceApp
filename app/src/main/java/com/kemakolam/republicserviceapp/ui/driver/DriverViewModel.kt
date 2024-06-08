package com.kemakolam.republicserviceapp.ui.driver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.data.network.model.DriversModel
import com.kemakolam.republicserviceapp.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(private val repository:ApiRepository):ViewModel(){

    //If json response starts with an Object use this -> MutableLiveData<Model>
    //If json response starts with an Array use this -> MutableLiveData<ArrayList<Model>>

    private val _driversList  = MutableLiveData<List<DriverModel?>?>()
    val driversList: LiveData<List<DriverModel?>?> = _driversList

    init {
        getAllDrivers()
    }

    private fun getAllDrivers() {

        viewModelScope.launch {
            val allDrivers = repository.getDrivers() //retrieves all drivers from API call
            if(allDrivers!=null){

                //print drivers to the console
                // Print drivers list to console
                println(allDrivers)

                //expose data to the view
                _driversList.postValue(allDrivers)
            }
        }
    }

}