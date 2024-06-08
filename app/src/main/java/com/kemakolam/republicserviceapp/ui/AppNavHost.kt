package com.kemakolam.republicserviceapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kemakolam.republicserviceapp.ui.driver.DriversScreen
import com.kemakolam.republicserviceapp.ui.driver_route_details.DriverRouteDetailsScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            DriversScreen(navController)
        }
        composable(
            "details/{driverId}",
            arguments = listOf(navArgument("driverId") { type = NavType.StringType })
        ) {  navBackStackEntry ->
            val driverId = navBackStackEntry.arguments?.getString("driverId")
            val driver = getDriverById(driverId)

            if (driver != null) {
                DriverRouteDetailsScreen(driver = driver) { navController.popBackStack() }
            } else {
                // Handle the case when the driver ID is not found
                // You can show an error message or navigate back to a default screen
            }
        }
    }
}

fun getDriverById(id: String?): Driver? {
    // Replace with your actual data source
    val items = listOf(
        Driver("1", "Driver 1"),
        Driver("2", "Driver 2"),
        Driver("3", "Driver 3")
    )
    return items.find { it.driverId == id }
}
