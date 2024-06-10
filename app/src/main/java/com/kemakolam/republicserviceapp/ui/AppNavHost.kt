package com.kemakolam.republicserviceapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import com.kemakolam.republicserviceapp.data.repository.ApiRepository
import com.kemakolam.republicserviceapp.ui.driver.DriverViewModel
import com.kemakolam.republicserviceapp.ui.driver.DriversScreen
import com.kemakolam.republicserviceapp.ui.driver_route_details.DriverRouteDetailsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@Composable
fun AppNavHost(
    navController: NavHostController,
    driverViewModel: DriverViewModel) {


    NavHost(navController, startDestination = "list") {
        composable("list") {
            DriversScreen(navController, driverViewModel)
        }
        composable(
            "details/{driverId}",
            arguments = listOf(
                navArgument("driverId") { type = NavType.StringType }

            )
        ) {  navBackStackEntry ->
            val driverId = navBackStackEntry.arguments?.getString("driverId")
            var driver = getDriverById(driverId,driverViewModel) //retrieve actual driver from the DB
            var routesList = runBlocking { driverViewModel.getRoutesDao()} //get list of routes

            // Find the route that matches the driver ID
            val matchingRoute = routesList.find { it.id.toString() == driverId }

            if(driver!=null && routesList!=null && driver?.id == matchingRoute?.id.toString()){
                //a.) If Driver Id is the same as Route Id, then display then display all route details on DriverRouteDetailsScreen

                //If both driver and route exist, display the associated route details
                DriverRouteDetailsScreen(driver = driver!!, route = matchingRoute!!) { navController.popBackStack() }
            }

            else if(driver!=null && routesList!=null && (driver?.id?.toInt()?.rem(2) ?:0)  == 0){
                //b.) If the driver id is divisible by 2 then show the first R type route
                val findFirstRTypeRoute = routesList.find { it.type == "R" }
                DriverRouteDetailsScreen(driver = driver!!, route = findFirstRTypeRoute!!) { navController.popBackStack() }
                Log.d("AppNavHost", "Divisible by 2")
            }
            else if(driver!=null && routesList!=null &&  (driver?.id?.toInt()?.rem(5) ?:0) == 0){
                //c.) If the driver id is divisible by 5 then display the second C type route
                val findSecondRTypeRoute = routesList.filter { it.type == "C" }.getOrNull(1) //only get routes with type C and get the 2nd one (getOrNull(1)
                DriverRouteDetailsScreen(driver = driver!!, route = findSecondRTypeRoute!!) { navController.popBackStack() }
                Log.d("AppNavHost", "Divisible by 5")
            }
            else {
                //d.) If driver id doesn't meet any of the conditions above then show the last I type route
                val findLastITypeRoute = routesList.filter { it.type == "I" }.lastOrNull() //lastOrNull will get the last route with type I
                DriverRouteDetailsScreen(driver = driver!!, route = findLastITypeRoute!!) { navController.popBackStack() }
            }



        }
    }
}


suspend fun getRoutesList(routesDao: RoutesDao):List<RouteEntity>{
    return routesDao.getAllRoutes()
}

suspend fun getRouteById(routeId: Int, routesDao: RoutesDao): RouteEntity? {
    return routesDao.getRouteById(routeId)
}

//Grab drivers from View Model
fun getDriverById(driverId: String?,driverViewModel: DriverViewModel): DriverEntity? {
    // Replace with your actual data source
    val driversList = driverViewModel.driversList.value //retrieved from view model
    return driversList?.find { it?.id == driverId }


}
