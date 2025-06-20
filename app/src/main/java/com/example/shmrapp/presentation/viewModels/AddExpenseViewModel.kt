package com.example.shmrapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.usecase.AddTranscationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class AddExpenseState(
    val accountId: Int = 211,
    val categoryId: Int? = null,
    val categoryName: String = "",
    val amount: String = "",
    val transactionDate: String = "",
    val transactionTime: String = "",
    val comment: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddExpenseViewModel(
    private val addTranscationUseCase: AddTranscationUseCase
): ViewModel() {
    private val _state = MutableStateFlow(AddExpenseState())
    val state = _state.asStateFlow()

    fun updateCategoryId(categoryId: Int) {
        _state.value = _state.value.copy(categoryId = categoryId)
    }

    fun updateCategoryName(categoryName: String) {
        _state.value = _state.value.copy(categoryName = categoryName)
    }

    fun updateAmount(amount: String) {
        _state.value = _state.value.copy(amount = amount)
    }

    fun updateDate(dateInMills: Long?) {
        if (dateInMills!=null){
            val date = Date(dateInMills)
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val formattedDate = sdf.format(date)
            _state.value = _state.value.copy(transactionDate = formattedDate)
        }
    }

    fun updateTime(time: String) {
        _state.value = _state.value.copy(transactionTime = time)
    }

    fun updateComment(comment: String) {
        _state.value = _state.value.copy(comment = comment)
    }

    fun createTransaction() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val dateTime = combineDateTime()
                val request = TransactionRequest(
                    accountId = 211,
                    categoryId = state.value.categoryId ?: return@launch,
                    amount = state.value.amount,
                    transactionDate = dateTime,
                    comment = state.value.comment.ifEmpty { null }
                )
                //addExpenseUseCase(request)
                Log.d("AddExpenseViewModel", "Transaction created: $request")
                _state.value = _state.value.copy(isLoading = false)
            } catch (e: Exception) {
                Log.d("AddExpenseViewModel", "Error creating transaction: ${e.message}")
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun combineDateTime() : String {
        val date = state.value.transactionDate
        val time = "20:00:00"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val dt = sdf.parse("$date $time") ?: Date()
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(dt)
    }
}