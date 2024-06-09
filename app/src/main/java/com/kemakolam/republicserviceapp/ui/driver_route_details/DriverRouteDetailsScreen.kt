package com.kemakolam.republicserviceapp.ui.driver_route_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity
import com.kemakolam.republicserviceapp.ui.Driver
import com.kemakolam.republicserviceapp.ui.Route


@Composable
fun DriverRouteDetailsScreen(
    driver: DriverEntity,
    route:RouteEntity,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${driver.name}'s",
            style = TextStyle(fontSize = 30.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            maxLines = 2
        )
        Text(
            text = "Route Details Screen",
            style = TextStyle(fontSize = 30.sp),
            modifier = Modifier.padding(bottom = 30.dp)
                .align(Alignment.CenterHorizontally),
            maxLines = 2
        )

        DriverDetailsCard(driver = driver, route = route)

    }
}

@Composable
fun DriverDetailsCard(driver: DriverEntity,  route:RouteEntity) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Text(
            text = "Route ID: ${route.id.toString()}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp)
        )
        Text(
            text = "Driver's Name: ${driver.name}",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp)
        )
        Text(
            text = "Routes Name: \n${route.name} ", // Add route name here
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp).padding(bottom = 10.dp)
        )
        Text(
            text = "Routes Type: ${route.type} ", // Add route type here
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DriverRouteDetailsScreenPreview(){

    //Dummy Data
    val dummyDrivers = listOf(
        DriverEntity(id= "1", name = "John Doe"),
        DriverEntity(id = "102", name = "Jane Smith")
    )

    val dummyRoutes = listOf(
        RouteEntity(
            id = 101,
            name = "West Side Industrial Route",
            type = "B"
        ),
        RouteEntity(
            id = 102,
            name = "South Side Commercial Route",
            type = "Z",

        )
    )


    DriverRouteDetailsScreen(dummyDrivers[0], dummyRoutes[0],{})

}