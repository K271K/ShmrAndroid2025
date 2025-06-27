package com.example.shmrapp.core.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmrapp.core.ui.components.MyListItemOnlyText
import com.example.shmrapp.theme.LightGreen
import com.example.shmrapp.core.ui.viewmodels.AddTransactionViewModel
import com.example.shmrapp.features.account.presentation.viewmodels.ArticlesViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreenContent(onClickTest: MutableState<() -> Unit>) {

    val viewModel: AddTransactionViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val articlesViewModel: ArticlesViewModel = koinViewModel()
    val articlesState by articlesViewModel.state.collectAsState()

    var showCategoryDialog by remember { mutableStateOf(false) }
    var showAmountDialog by remember { mutableStateOf(false) }
    var showDatePickerDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyListItemOnlyText(
            content = {
                Text(text = "Счёт")
            },
            trailContent = {
                Text(text = "Название счёта")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            },
            modifier = Modifier.height(70.dp)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Статья")
            },
            trailContent = {
                Text(text = state.categoryName)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            },
            modifier = Modifier.height(70.dp),
            onClick = {
                showCategoryDialog = true
            }
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Сумма")
            },
            trailContent = {
                Text(text = state.amount + " ₽")
            },
            modifier = Modifier.height(70.dp),
            onClick = {
                showAmountDialog = true
            }
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Дата")
            },
            trailContent = {
                Text(text = state.transactionDate)
            },
            modifier = Modifier.height(70.dp),
            onClick = {
                showDatePickerDialog = true
            }
        )
        HorizontalDivider()
    }

    if (showCategoryDialog) {
        BasicAlertDialog(
            onDismissRequest = { showCategoryDialog = false }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp)
                    .background(LightGreen)
            ) {
                LazyColumn {
                    items(articlesState.articles) {
                        Text(
                            text = it.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.updateCategoryId(it.id)
                                    viewModel.updateCategoryName(it.name)
                                    showCategoryDialog = false
                                }
                                .padding(16.dp)
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }

    if (showAmountDialog) {
        var number by remember { mutableStateOf(state.amount) } // Синхронизируем с state
        AlertDialog(
            onDismissRequest = {showAmountDialog = false},
            title = {Text(text = "Введите сумму")},
            text = {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(
                        value = number,
                        onValueChange = { number = it },
                        label = { Text("Сумма") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.updateAmount(number)
                        showAmountDialog = false
                    },
                    enabled = number.isNotEmpty() // Делаем кнопку активной только при вводе
                ) {
                    Text("Подтвердить")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showAmountDialog = false }
                ) {
                    Text("Закрыть")
                }
            }
        )
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {showDatePickerDialog = false},
            confirmButton = {
                TextButton(onClick = {
                    viewModel.updateDate(datePickerState.selectedDateMillis)
                    showDatePickerDialog = false
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerDialog = false }) {
                    Text(text = "Отмена")
                }
            },
            colors = DatePickerDefaults.colors().copy(
                containerColor = LightGreen,
            )
        ) {
            DatePicker(state = datePickerState)
        }
    }

}