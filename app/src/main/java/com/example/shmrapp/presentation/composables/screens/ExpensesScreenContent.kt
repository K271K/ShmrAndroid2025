package com.example.shmrapp.presentation.composables.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import com.example.shmrapp.presentation.composables.MyListItemOnlyText
import com.example.shmrapp.presentation.composables.MyListItemWithLeadIcon
import com.example.shmrapp.presentation.theme.LightGreen
import com.example.shmrapp.presentation.viewModels.ExpensesViewModel
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("RestrictedApi")
@Composable
fun ExpensesScreenContent() {

    var backPressedTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current

    val viewModel: ExpensesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 3000L) {
            (context as? ComponentActivity)?.finish()
        } else {
            Toast.makeText(context, "To exit press back again", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
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
                MyListItemOnlyText(
                    modifier = Modifier.height(70.dp).background(LightGreen),
                    content = {
                        Text(text = "Всего")
                    },
                    trailContent = {
                        Text(text = state.totalAmount.toString())
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                )
                HorizontalDivider()
                LazyColumn {
                    items(state.expenses) { expense ->
                        MyListItemWithLeadIcon(
                            modifier = Modifier.height(70.dp),
                            icon = expense.icon,
                            iconBg = LightGreen,
                            content = {
                                Column {
                                    Text(
                                        text = expense.label
                                    )
                                    if (!expense.comment.isNullOrEmpty()) {
                                        Text(
                                            text = expense.comment,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                            },
                            trailContent = {
                                Text(
                                    text = expense.amount
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                )
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }

}