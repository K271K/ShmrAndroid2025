package com.example.shmrapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shmrapp.presentation.composables.screens.AccountScreenContent
import com.example.shmrapp.presentation.composables.screens.AddExpenseScreenContent
import com.example.shmrapp.presentation.composables.screens.ArticlesScreenContent
import com.example.shmrapp.presentation.composables.screens.ExpenseHistoryScreenContent
import com.example.shmrapp.presentation.composables.screens.ExpensesScreenContent
import com.example.shmrapp.presentation.composables.screens.IncomeScreenContent
import com.example.shmrapp.presentation.composables.screens.SettingsScreenContent

@Composable
fun NavScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        topBar = {
            FinanceTopAppBar(currentRoute, navController)
        },
        floatingActionButton = {
            FinanceFloatingActionButton(currentRoute, navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.Expenses,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<ScreenRoutes.Expenses> {
                ExpensesScreenContent()
            }
            composable<ScreenRoutes.Income> {
                IncomeScreenContent()
            }
            composable<ScreenRoutes.Account> {
                AccountScreenContent()
            }
            composable<ScreenRoutes.Articles> {
                ArticlesScreenContent()
            }
            composable<ScreenRoutes.Settings> {
                SettingsScreenContent()
            }
            composable<ScreenRoutes.ExpenseHistory> {
                ExpenseHistoryScreenContent()
            }
            composable<ScreenRoutes.AddExpense> {
                AddExpenseScreenContent()
            }
        }
    }
}


