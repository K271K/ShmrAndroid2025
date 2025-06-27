package com.example.shmrapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.shmrapp.features.account.presentation.screens.AccountScreenContent
import com.example.shmrapp.core.ui.screens.AddTransactionScreenContent
import com.example.shmrapp.features.articles.presentation.screens.ArticlesScreenContent
import com.example.shmrapp.core.ui.screens.TransactionHistoryScreenContent
import com.example.shmrapp.features.expense.presentation.screens.ExpensesScreen
import com.example.shmrapp.features.incomes.presentation.screens.IncomeScreen
import com.example.shmrapp.features.settings.presentation.screens.SettingsScreenContent

@Composable
fun NavScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //Действие которое не связано с навигацией (например подтверждение создания нового расхода)
    val topAppBarAction = remember {
        mutableStateOf({})
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        topBar = {
            FinanceTopAppBar(currentRoute, navController, topAppBarAction.value)
        },
        floatingActionButton = {
            FinanceFloatingActionButton(currentRoute, navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "expenses",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("expenses") {
                ExpensesScreen()
            }
            composable("incomes") {
                IncomeScreen()
            }
            composable("account") {
                AccountScreenContent()
            }
            composable("articles") {
                ArticlesScreenContent()
            }
            composable("settings") {
                SettingsScreenContent()
            }
            composable(
                "transactionHistory?isIncome={isIncome}",
                arguments = listOf(navArgument("isIncome") { type = NavType.BoolType })
            ) { backStackEntry ->
                val isIncome = backStackEntry.arguments?.getBoolean("isIncome") ?: false
                TransactionHistoryScreenContent(isIncome)
            }
            composable("addExpense") {
                AddTransactionScreenContent(topAppBarAction)
            }
        }
    }
}


