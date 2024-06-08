package com.kemakolam.republicserviceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kemakolam.republicserviceapp.ui.AppNavHost
import com.kemakolam.republicserviceapp.ui.driver.DriversScreen
import com.kemakolam.republicserviceapp.ui.theme.RepublicServiceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServiceAppTheme {

                val navController = rememberNavController()
                AppNavHost(navController = navController)

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