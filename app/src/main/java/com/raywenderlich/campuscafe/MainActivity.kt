package com.raywenderlich.campuscafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raywenderlich.campuscafe.ui.navigation.AppNavigation
import com.raywenderlich.campuscafe.ui.theme.CampusCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusCafeTheme {
                AppNavigation()
            }
        }
    }
}
