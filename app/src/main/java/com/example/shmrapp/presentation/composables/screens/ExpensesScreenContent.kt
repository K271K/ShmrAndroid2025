package com.example.shmrapp.presentation.composables.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ComponentActivity

@SuppressLint("RestrictedApi")
@Preview(showBackground = true)
@Composable
fun ExpensesScreenContent() {
    var backPressedTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current
    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 3000L) {
            (context as? ComponentActivity)?.finish()
        } else {
            Toast.makeText(context, "To exit press back again", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    }
    /*if (viewModel.isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MyListItemOnlyText(
                modifier = Modifier
                    .height(56.dp)
                    .background(LightGreen),
                content = {
                    Text(text = "Всего")
                },
                trailContent = {
                    Text(text = "436 558 R")
                }
            )
            HorizontalDivider()
            LazyColumn {
                items(viewModel.expensesList.value) { expense ->
                    MyListItemWithLeadIcon(
                        modifier = Modifier
                            .height(70.dp),
                        icon = expense.icon,
                        iconBg = LightGreen,
                        content = {
                            Column {
                                Text(text = expense.label)
                                if (!expense.comment.isNullOrEmpty()) {
                                    Text(
                                        text = expense.comment,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier
                                            .padding(top = 4.dp)
                                    )
                                }
                            }
                        },
                        trailContent = {
                            Text(text = expense.amount)
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                                contentDescription = "Подробнее",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }*/
}