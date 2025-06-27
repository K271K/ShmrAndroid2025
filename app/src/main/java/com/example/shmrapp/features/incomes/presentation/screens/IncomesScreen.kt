package com.example.shmrapp.features.incomes.presentation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shmrapp.Constants.TIME_TO_PRESS_BACK_AGAIN
import com.example.shmrapp.core.ui.components.MyListItemOnlyText
import com.example.shmrapp.theme.LightGreen
import com.example.shmrapp.features.incomes.presentation.viewmodels.IncomeViewModel
import com.example.shmrapp.features.incomes.presentation.viewmodels.IncomesTodayState
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("RestrictedApi")
@Preview(showBackground = true)
@Composable
fun IncomeScreen() {
    var backPressedTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current

    val viewModel: IncomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < TIME_TO_PRESS_BACK_AGAIN) {
            (context as? ComponentActivity)?.finish()
        } else {
            Toast.makeText(context, "To exit press back again", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    }

    IncomesContent(
        state = state
    )
}

@Composable
private fun IncomesContent(
    state: IncomesTodayState
){
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.error, color = MaterialTheme.colorScheme.error)
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
                    items(state.incomes) { income ->
                        MyListItemOnlyText(
                            modifier = Modifier.height(73.dp),
                            content = {
                                Text(text = income.label)
                            },
                            trailContent = {
                                Text(text = income.amount + "₽")
                                Spacer(modifier = Modifier.width(16.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        )
                        HorizontalDivider()
                    }
                }
                HorizontalDivider()
            }
        }
    }
}