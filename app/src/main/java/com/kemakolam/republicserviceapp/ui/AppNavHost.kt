package com.kemakolam.republicserviceapp.ui

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun AppNavHost(
    navController: NavHostController,
    driverViewModel: DriverViewModel,
    driverDao: DriversDao,
    routesDao: RoutesDao) {


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
            //val driver = driverId?.let { getDriverById(it, driverViewModel) }


            var driver by remember { mutableStateOf<DriverEntity?>(null) }
            var route by remember { mutableStateOf<RouteEntity?> (null)}

                LaunchedEffect(driverId) {
                    if(driverId!=null){
                        driver = driverDao.getDriverById(driverId)
                        route = driver?.id?.let { routesDao.getRouteById(it.toInt()) }

                    }
                }

                if(driver!=null && route!=null && driver?.id == route?.id.toString()){
                    //a.) If Driver Id is the same as Route Id, then display then display all route details on DriverRouteDetailsScreen

                    //If both driver and route exist, display the details screen

                    DriverRouteDetailsScreen(driver = driver!!, route = route!!) {
                        navController.popBackStack()
                    }
                }else{
                    Text(
                        text = "Loading data or IDs do not match..."
                    )
                }


        }
    }
}

fun getRouteById(routeId: Int?): RouteEntity {
    TODO()
}

//Grab drivers from View Model
fun getDriverById(driverId: String?,driverViewModel: DriverViewModel): DriverEntity? {
    // Replace with your actual data source
    val driversList = driverViewModel.driversList.value
    return driversList?.find { it?.id == driverId }


}
