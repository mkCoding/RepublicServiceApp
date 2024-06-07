package com.kemakolam.republicserviceapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun RoutesScreen(){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Routes Screen",
            style = TextStyle(fontSize = 30.sp)
        )

        Button(
            onClick = { /*TODO*/ },

        ) {
            Text(text = "Click Me!")

        }
    }

}


@Preview(showBackground = true)
@Composable
fun RoutesScreenPreview(){
    RoutesScreen()

}