package com.raywenderlich.campuscafe.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
    fun HomeScreen(
    onMenuClick: () -> Unit
    ) {

        var points by remember {
            mutableIntStateOf(0)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center
        ) {
            Text( text = "Campus Cafe" )
            Text( text = "Welcome, Students" )
            Text( text = "Points: $points" )
            Button(
                onClick = onMenuClick
            ) {
                Text( text = "View Menu" )
            }
        }
    }