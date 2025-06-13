package com.example.shmrapp.presentation.navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.shmrapp.R

@Composable
fun FinanceFloatingActionButton(
    currentRoute: String?,
    navController: NavController
) {
    when (currentRoute) {
        ScreenRoutes.Expenses::class.qualifiedName -> {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = colorResource(R.color.green_dark),
                contentColor = colorResource(R.color.white),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        }
        ScreenRoutes.Income::class.qualifiedName -> {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = colorResource(R.color.green_dark),
                contentColor = colorResource(R.color.white),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        }
        ScreenRoutes.Account::class.qualifiedName -> {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = colorResource(R.color.green_dark),
                contentColor = colorResource(R.color.white),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        }
        else -> {

        }
    }

}