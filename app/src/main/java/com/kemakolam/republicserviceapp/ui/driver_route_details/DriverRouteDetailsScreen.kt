package com.kemakolam.republicserviceapp.ui.driver_route_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.db.tables.RouteEntity


@Composable
fun DriverRouteDetailsScreen(
    driver: DriverEntity,
    route:RouteEntity,
    onBack: () -> Unit
) {

    //Custom Color
    val customColor = Color(0xFF5C5346) // Custom color using RGB values

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = customColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${driver.name}'s",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
//            maxLines = 2
        )
        Text(
            text = "Route Details Screen",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray // Change color to suit your design
            ),
            modifier = Modifier.padding(bottom = 30.dp)
        )

        DriverDetailsCard(driver = driver, route = route, onBack)

    }
}

@Composable
fun DriverDetailsCard(driver: DriverEntity,  route:RouteEntity, onBack: () -> Unit) {

    Card(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp),
        elevation = CardDefaults.cardElevation(20.dp)

    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Route ID: ${route.id}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

           //Route Name Section
            Text(
                text = "Route Name:",
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "${route.name}",
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontStyle = FontStyle.Italic),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            //Route Type Section
            Text(
                text = "Route Type:",
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "${route.type}",
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontStyle = FontStyle.Italic),
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Driver's Name: ${driver.name}",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Add more Text composables for additional driver details if needed


        }

    }


backButton(onBack)



}

@Composable
fun backButton(onBack: () -> Unit){

    Column( modifier = Modifier
        .wrapContentHeight()

    ){

//        Spacer(modifier = Modifier.height(50.dp)) // Add a spacer above the button
        Button(
            onClick = {onBack()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 2.dp,
                focusedElevation = 4.dp
            )

        ){
            Row (

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 1.dp)

                )
                Spacer(modifier = Modifier.width(80.dp))

                Text(
                    text = "Back",
                    style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
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