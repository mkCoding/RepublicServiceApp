package com.kemakolam.republicserviceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kemakolam.republicserviceapp.data.db.dao.DriversDao
import com.kemakolam.republicserviceapp.data.db.dao.RoutesDao
import com.kemakolam.republicserviceapp.ui.AppNavHost
import com.kemakolam.republicserviceapp.ui.driver.DriverViewModel
import com.kemakolam.republicserviceapp.ui.driver.DriversScreen
import com.kemakolam.republicserviceapp.ui.theme.RepublicServiceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var driversDao: DriversDao

    @Inject
    lateinit var routesDao: RoutesDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServiceAppTheme {

                val navController = rememberNavController()
                val driverViewModel: DriverViewModel = hiltViewModel()
                val driversDao: DriversDao = driversDao
                val routesDao: RoutesDao = routesDao

                AppNavHost(navController = navController, driverViewModel = driverViewModel, driversDao, routesDao)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DriversPreview(){
    val navController = rememberNavController()
//    DriversScreen(navController = navController)

}