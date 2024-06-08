package com.kemakolam.republicserviceapp.ui

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kemakolam.republicserviceapp.ui.Route


@Composable
fun DriverRouteDetailsScreen(
    driverInfo:List<Driver>,
    routeInfo:List<Route>,
    onBack: () -> Unit
){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(text = "Routes Details Screen", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(bottom = 30.dp))

        Card (
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp),
            elevation = CardDefaults.cardElevation(20.dp)

        ) {
            Text(text = "Route ID:", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp))
            Text(text = "Driver's Name : ", style = TextStyle(fontSize = 30.sp), modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp))
            Text(text = "Routes Name: ", style = TextStyle(fontSize = 30.sp),  modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp))
            Text(text = "Routes Type: ", style = TextStyle(fontSize = 30.sp),  modifier = Modifier.padding(start = 20.dp))
        }









        val context = LocalContext.current
        Button(
            onClick = {  Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()},

        ) {
            Text(text = "Click Me!")

        }
    }

}


@Preview(showBackground = true)
@Composable
fun DriverRouteDetailsScreenPreview(){

    //Dummy Data
    val dummyDrivers = listOf(
        Driver(driverId = "1", driverName = "John Doe"),
        Driver(driverId = "102", driverName = "Jane Smith")
    )

    val dummyRoutes = listOf(
        Route(
            id = 101,
            name = "West Side Industrial Route",
            type = "B"
        ),
        Route(
            id = 102,
            name = "South Side Commercial Route",
            type = "Z",

        )
    )


    DriverRouteDetailsScreen(dummyDrivers,dummyRoutes, {})

}