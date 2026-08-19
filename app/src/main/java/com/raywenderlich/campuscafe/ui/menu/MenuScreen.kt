package com.raywenderlich.campuscafe.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem

@Composable
fun MenuScreen() {
    val menuItems = listOf(
        MenuItem(
            id = 1,
            name = "coffee",
            price = 2.50
        ),
        MenuItem(
            id = 2,
            name = "tea",
            price = 2.00
        ),
        MenuItem(
            id = 3,
            name = "SourCreme Glazed Donut",
            price = 1.75
        ),
        MenuItem(
            id = 4,
            name = "Italian Meatball Sandwich",
            price = 6.50
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = "Campus Cafe Menu" )
        LazyColumn(
             verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(menuItems) { item ->
                Text( text = "${ item.name } - $${ item.price }")
            }
        }
    }
}