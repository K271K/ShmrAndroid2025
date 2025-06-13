package com.example.shmrapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoutes {
    @Serializable
    object Expenses : ScreenRoutes()

    @Serializable
    object Income : ScreenRoutes()

    @Serializable
    object Account : ScreenRoutes()

    @Serializable
    object Articles : ScreenRoutes()

    @Serializable
    object Settings : ScreenRoutes()

    @Serializable
    object ExpenseHistory : ScreenRoutes()

    @Serializable
    object IncomeHistory : ScreenRoutes()

    @Serializable
    object AddExpense : ScreenRoutes()

    @Serializable
    object AnalyticsScreen : ScreenRoutes()
}