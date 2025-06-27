package com.example.shmrapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
    navController: NavController,
    value: () -> Unit,
) {
    when (currentRoute) {
        "expenses" -> {
            MyTopAppBar(
                title = "Расходы сегодня",
                rightButton = {
                    IconButton(
                        onClick = {
                            navController.navigate("transactionHistory?isIncome=false")
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
        "account" -> {
            MyTopAppBar(
                title = "Мой счёт",
                rightButton = {
                    IconButton(
                        onClick = {
                            //TODO
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Go to bank account edit"
                        )
                    }
                }
            )
        }
        "articles" -> {
            MyTopAppBar(
                title = "Мои статьи"
            )
        }
        "incomes" -> {
            MyTopAppBar(
                title = "Доходы сегодня",
                rightButton = {
                    IconButton(
                        onClick = {
                            navController.navigate("transactionHistory?isIncome=true")
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
        "settings" -> {
            MyTopAppBar(
                title = "Настройки"
            )
        }
        "transactionHistory?isIncome={isIncome}" -> {
            MyTopAppBar(
                title = "Моя история",
                rightButton = {
                    IconButton(
                        onClick = {
                            //TODO: Go to history
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.graphics_icon),
                            contentDescription = "Go to history"
                        )
                    }
                },
                leftButton = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go to history"
                        )
                    }
                }
            )
        }
        "addExpense" -> {
            MyTopAppBar(
                title = "Моя расходы",
                rightButton = {
                    IconButton(
                        onClick = {
                            value.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Добавить расход"
                        )
                    }
                },
                leftButton = {
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoutes.Expenses){
                                popUpTo(0)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Go to history"
                        )
                    }
                }
            )
        }
        else -> {
            Log.d("MyTopAppBar", "Unknown route: $currentRoute")
//            MyTopAppBar(
//                title = "Моя история",
//                rightButton = {
//                    IconButton(
//                        onClick = {
//                            //TODO: Go to history
//                        }
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.graphics_icon),
//                            contentDescription = "Go to history"
//                        )
//                    }
//                },
//                leftButton = {
//                    IconButton(
//                        onClick = {
//                            navController.popBackStack()
//                        }
//                    ) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
//                            contentDescription = "Go to history"
//                        )
//                    }
//                }
//            )
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