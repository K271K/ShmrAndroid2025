package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shmrapp.presentation.composables.MyListItemOnlyText
import com.example.shmrapp.presentation.composables.MyListItemWithLeadIcon
import com.example.shmrapp.presentation.theme.LightGreen
import com.example.shmrapp.presentation.viewModels.ExpenseHistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseHistoryScreenContent() {
    val viewModel: ExpenseHistoryViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    var showStartDatePickerDialog by remember { mutableStateOf(false) }
    var showEndDatePickerDialog by remember { mutableStateOf(false) }

    val startDatePickerState = rememberDatePickerState()
    val endDatePickerState = rememberDatePickerState()

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error ?: "Неизвестная ошибка", color = MaterialTheme.colorScheme.error)
            }
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                MyListItemOnlyText(
                    modifier = Modifier
                        .height(56.dp)
                        .background(LightGreen),
                    content = {
                        Text(
                            text = "Начало"
                        )
                    },
                    trailContent = {
                        Text(text = state.startDate)
                    },
                    onClick = {
                        showStartDatePickerDialog = true
                    }
                )
                HorizontalDivider()
                MyListItemOnlyText(
                    modifier = Modifier
                        .height(56.dp)
                        .background(LightGreen),
                    content = {
                        Text(
                            text = "Конец"
                        )
                    },
                    trailContent = {
                        Text(text = state.endDate)
                    },
                    onClick = {
                        showEndDatePickerDialog = true
                    }
                )
                HorizontalDivider()
                MyListItemOnlyText(
                    modifier = Modifier
                        .height(56.dp)
                        .background(LightGreen),
                    content = {
                        Text(
                            text = "Сумма"
                        )
                    },
                    trailContent = {
                        Text(text = state.amount.toString() + "₽")
                    }
                )
                HorizontalDivider()
                LazyColumn {
                    items(state.transactions) { transaction->
                        MyListItemWithLeadIcon(
                            modifier = Modifier
                                .height(69.dp),
                            content = {
                                Column {
                                    Text(text = transaction.label)
                                    if (transaction.comment.isNotEmpty()){
                                        Text(text = transaction.comment)
                                    }
                                }
                            },
                            trailContent = {
                                Column {
                                    Text(text = transaction.amount + "₽")
                                    Text(text = transaction.time)
                                }
                            },
                            icon = transaction.icon,
                            iconBg = LightGreen
                        )
                        HorizontalDivider()
                    }
                }
                if (showStartDatePickerDialog) {
                    DatePickerDialog(
                        onDismissRequest = {showStartDatePickerDialog = false},
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.updateStartDate(startDatePickerState.selectedDateMillis)
                                showStartDatePickerDialog = false
                            }) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showStartDatePickerDialog = false }) {
                                Text(text = "Отмена")
                            }
                        },
                        colors = DatePickerDefaults.colors().copy(
                            containerColor = LightGreen,
                        )
                    ) {
                        DatePicker(state = startDatePickerState)
                    }
                }
                if (showEndDatePickerDialog) {
                    DatePickerDialog(
                        onDismissRequest = {showEndDatePickerDialog = false},
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.updateEndDate(endDatePickerState.selectedDateMillis)
                                showEndDatePickerDialog = false
                            }) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showEndDatePickerDialog = false }) {
                                Text(text = "Отмена")
                            }
                        },
                        colors = DatePickerDefaults.colors().copy(
                            containerColor = LightGreen,
                        )
                    ) {
                        DatePicker(state = endDatePickerState)
                    }
                }
            }
        }
    }


}