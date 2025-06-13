package com.example.shmrapp.presentation.navigation

import com.example.shmrapp.R

data class BottomNavItem(
    val route: Any,
    val icon: Int,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = ScreenRoutes.Expenses,
        icon = (R.drawable.ic_rashod_gray),
        label = "Расходы"
    ),
    BottomNavItem(
        route = ScreenRoutes.Income,
        icon = (R.drawable.ic_dohod_gray),
        label = "Доходы"
    ),
    BottomNavItem(
        route = ScreenRoutes.Account,
        icon = (R.drawable.ic_calc_gray),
        label = "Счёт"
    ),
    BottomNavItem(
        route = ScreenRoutes.Articles,
        icon = (R.drawable.ic_articles_gray),
        label = "Статьи"
    ),
    BottomNavItem(
        route = ScreenRoutes.Settings,
        icon = (R.drawable.ic_settings_gray),
        label = "Настройки"
    )
)