package com.kemakolam.republicserviceapp.ui

import android.content.ClipData.Item
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kemakolam.republicserviceapp.R

@Composable
fun DriversScreen(){

    //Keep track on whether sorting is applied
    var isSorted by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Drivers Screen",
            style = TextStyle(fontSize = 30.sp)
        )


        Icon(
            imageVector= Icons.Default.Add,
            contentDescription = null, // Decorative element
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .clickable(onClick = {isSorted = !isSorted  })
                .fillMaxWidth()
                .align(alignment = Alignment.End),

            ) {
            Image(
                painter = painterResource(id = R.drawable.sort_icon),
                contentDescription = null, // Decorative element
                modifier = Modifier.size(50.dp)
            )


        }

        val items = listOf(
            Driver("1", "John Smith"),
            Driver("2", "Alice Johnson"),
            Driver("3", "Bob Williams"),
            Driver("4", "Charlie Jones"),
            Driver("5", "David Brown")
        )


        DriversList(drivers = if (isSorted) items.sortedBy { it.driverName.split(" ").last() } else items )

    }

}


data class Driver(val driverId: String, val driverName: String)

@Composable
fun DriversList(drivers:List<Driver>){
    LazyColumn {
        items(drivers) { itemiuk ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
                // Customize card background color if needed
            ){
                Row (
                    modifier = Modifier.height(70.dp)
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = itemiuk.driverName,
                        modifier = Modifier,
                        style = TextStyle(fontSize = 20.sp)
                    )

                    //Text(text = "Number")


                }
            }
        }
    }
}

@Preview( showBackground = true)
@Composable
fun DriversScreenPreview(){
    DriversScreen()
}