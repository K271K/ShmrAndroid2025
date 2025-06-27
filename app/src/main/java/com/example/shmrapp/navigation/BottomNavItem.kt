package com.example.shmrapp.navigation

import com.example.shmrapp.R

data class BottomNavItem(
    val route: String,
    val icon: Int,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = "expenses",
        icon = (R.drawable.ic_rashod_gray),
        label = "Расходы"
    ),
    BottomNavItem(
        route = "incomes",
        icon = (R.drawable.ic_dohod_gray),
        label = "Доходы"
    ),
    BottomNavItem(
        route = "account",
        icon = (R.drawable.ic_calc_gray),
        label = "Счёт"
    ),
    BottomNavItem(
        route = "articles",
        icon = (R.drawable.ic_articles_gray),
        label = "Статьи"
    ),
    BottomNavItem(
        route = "settings",
        icon = (R.drawable.ic_settings_gray),
        label = "Настройки"
    )
)