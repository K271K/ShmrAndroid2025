package com.example.shmrapp.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.shmrapp.R

@Composable
fun FinanceTopAppBar(
    currentRoute: String?,
    navController: NavController
) {
    when (currentRoute) {
        ScreenRoutes.Account::class.qualifiedName -> {
            MyTopAppBar(
                title = "Мой счёт",
                rightButton = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Go to bank account edit"
                        )
                    }
                }
            )
        }

        ScreenRoutes.Articles::class.qualifiedName -> {
            MyTopAppBar(
                title = "Мои статьи"
            )
        }

        ScreenRoutes.Expenses::class.qualifiedName -> {
            MyTopAppBar(
                title = "Расходы сегодня",
                rightButton = {
                    IconButton(
                        onClick = {
                            //TODO: Go to history
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mdi_history),
                            contentDescription = "Go to history"
                        )
                    }
                }
            )
        }

        ScreenRoutes.Income::class.qualifiedName -> {
            MyTopAppBar(
                title = "Доходы сегодня",
                rightButton = {
                    IconButton(
                        onClick = {
                            //TODO: Go to history
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mdi_history),
                            contentDescription = "Go to history"
                        )
                    }
                }
            )
        }

        ScreenRoutes.Settings::class.qualifiedName -> {
            MyTopAppBar(
                title = "Настройки"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    rightButton: @Composable (RowScope.() -> Unit) = {},
    leftButton: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        colors = TopAppBarColors(
            containerColor = colorResource(R.color.green_dark),
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ),
        navigationIcon = {
            leftButton()
        },
        actions = {
            rightButton()
        }
    )
}