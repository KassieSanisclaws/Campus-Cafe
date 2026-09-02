package com.raywenderlich.campuscafe.ui.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckOutScreen(
    total: Double,
    pointsEarned: Int,
    onDoneClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Order Complete!")
        Text(text = "Thank You For Visiting Campus Cafe!")
        Text(text = "Total Paid: $${"%.2f" .format(total)}", modifier = Modifier.padding(top = 16.dp))
        Text(text = "Points Earned: $pointsEarned", modifier = Modifier.padding(top = 8.dp))
        Button(
            onClick = onDoneClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Done")
        }
    }
}