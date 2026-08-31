package com.raywenderlich.campuscafe.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem

@Composable
fun MenuDetailsScreen(
       item: MenuItem,
       onBackClick: () -> Unit,
       onAddToOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
    ) {
        Text( text = item.name )
        Text( text = "Category: ${ item.category }")
        Text( text = "Price: $${ item.price }")
        Button(
            onClick = onAddToOrder,
            modifier = Modifier.padding( top = 16.dp )
        ) {
            Text( text = "Add To Order" )
        }
        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Back to Menu"
            )
        }
    }
}