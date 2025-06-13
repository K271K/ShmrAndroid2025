package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.models.BankAccountModel

val bankAccountsMockList = listOf(
    BankAccountModel(
        label = "Валюта",
        currency = "₽"
    ),
)

@Preview(showBackground = true)
@Composable
fun AccountScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.green_light))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.green_light),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(3.dp),
            ) {
                Text(
                    text = "\uD83D\uDCB0",
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Баланс",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "-670 000 ₽",
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Right arrow",
                )
            }
        }
        HorizontalDivider()
        LazyColumn {
            items(bankAccountsMockList) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.green_light))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = it.label, modifier = Modifier.weight(1f))
                    Row {
                        Text(text = it.currency)
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "ArrowForward"
                        )
                    }
                }
                HorizontalDivider()

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.mock_diagram),
            contentDescription = "Diagramm",
            modifier = Modifier.height(300.dp).width(600.dp),
            contentScale = ContentScale.Fit
        )
    }
}

