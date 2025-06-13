package com.example.shmrapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shmrapp.presentation.theme.ShmrAppTheme
import com.example.shmrapp.presentation.navigation.NavScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShmrAppTheme {
                NavScreen()
            }
        }
    }
}

