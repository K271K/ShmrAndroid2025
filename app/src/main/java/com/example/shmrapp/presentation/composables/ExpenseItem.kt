package com.example.shmrapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.models.ExpenseModel

@Composable
fun ExpenseItem(expense: ExpenseModel) {
    ListItem(
        lead = {
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.green_light), shape = RoundedCornerShape(16.dp))
                    .padding(3.dp)
            ) {
                Text(text = expense.icon)
            }
        },
        content = {
            Column {
                Text(text = expense.label, style = MaterialTheme.typography.bodyLarge)
                if (expense.comment.isNotEmpty()) {
                    Text(text = expense.comment, style = MaterialTheme.typography.bodyMedium)
                }
            }
        },
        trail = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = expense.amount, style = MaterialTheme.typography.bodyLarge)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    )
}
