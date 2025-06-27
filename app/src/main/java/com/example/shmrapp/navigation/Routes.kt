package com.example.shmrapp.navigation

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
    data class TransactionHistory (val isIncome: Boolean) : ScreenRoutes()

    @Serializable
    object AddExpense : ScreenRoutes()
}