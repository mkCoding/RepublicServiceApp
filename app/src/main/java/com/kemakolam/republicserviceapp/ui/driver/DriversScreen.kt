package com.kemakolam.republicserviceapp.ui.driver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kemakolam.republicserviceapp.R
import com.kemakolam.republicserviceapp.data.db.tables.DriverEntity
import com.kemakolam.republicserviceapp.data.network.model.DriverModel
import com.kemakolam.republicserviceapp.ui.Driver
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun DriversScreen(navController: NavController, driverViewModel: DriverViewModel){
    /*
           Pass to param based on needs
           previewViewModel - Preview
            - (uncomment the @Preview Method)
            - Change param of this method to PreviewDriverViewModel
           driverViewModel - App Running
     */

    //val previewViewModel = hiltViewModel<PreviewDriverViewModel>()





    val driversList by driverViewModel.driversList.observeAsState() //variable to accessing list from view model
    println(driversList)



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
                .clickable(onClick = { isSorted = !isSorted })
                .fillMaxWidth()
                .align(alignment = Alignment.End),

            ) {
            Image(
                painter = painterResource(id = R.drawable.sort_icon),
                contentDescription = null, // Decorative element
                modifier = Modifier.size(50.dp)
            )


        }


        //These names ultimately need to be pulled from the database
        //and passed here
        val dummyItems = listOf(
            Driver("1", "John Smith"),
            Driver("2", "Alice Johnson"),
            Driver("3", "Bob Williams"),
            Driver("4", "Charlie Jones"),
            Driver("5", "David Brown"),
            Driver("6", "Justin Thyme"),
            Driver("7", "Anita Bath"),
            Driver("8", "Rusty Pipes"),
            Driver("9", "Dee Zaster"),
            Driver("10", "Paige Turner")


        )


        DriversList(navController = navController,drivers = if (isSorted) driversList?.sortedBy { it?.name?.split(" ")?.last() } else driversList )
        //DriversList(navController = navController,drivers = if (isSorted) dummyItems.sortedBy { it.driverName.split(" ").last() } else dummyItems )

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversList(navController: NavController, drivers:List<DriverEntity?>?){
    LazyColumn {
        items(drivers ?: emptyList()) { itemiuk ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
                    .border(
                        BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(16.dp)

                    ),
                elevation = CardDefaults.cardElevation(20.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    // Navigate to driver details when card is clicked
                    navController.navigate("details/${itemiuk?.id}")
                }

                // Customize card background color if needed
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(70.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .background(Color.White)

                ){
                    Text(

                        text = itemiuk?.id?:"",
                        modifier = Modifier
                            .padding(start = 20.dp),
                        style = TextStyle(fontSize = 20.sp)
                    )
                    Text(
                        text = itemiuk?.name?:"",
                        modifier = Modifier
                            .padding(start = 16.dp),
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
//    val navController = rememberNavController()
//    DriversScreen(navController = navController)

    val navController = rememberNavController()
    val previewViewModel = PreviewDriverViewModel() // Mock ViewModel instance

  //  DriversScreen(navController = navController, previewViewModel)
}


// Mock ViewModel for preview purposes
class PreviewDriverViewModel : ViewModel() {
    val driversList = MutableLiveData<List<DriverEntity>>(listOf(
        DriverEntity(1,"1", "John Smith"),
        DriverEntity(2,"2", "Alice Johnson"),
        DriverEntity(3,"3", "Bob Williams"),
        DriverEntity(4,"4", "Charlie Jones"),
        DriverEntity(5,"5", "David Brown"),
        DriverEntity(6,"6", "Justin Thyme"),
        DriverEntity(7,"7", "Anita Bath"),
        DriverEntity(8,"8", "Rusty Pipes"),
        DriverEntity(9,"9", "Dee Zaster"),
        DriverEntity(10,"10", "Paige Turner")
    ))
}


