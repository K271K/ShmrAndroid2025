package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.composables.MyListItemOnlyText
import com.example.shmrapp.presentation.composables.MyListItemWithLeadIcon
import com.example.shmrapp.presentation.theme.DarkGreen
import com.example.shmrapp.presentation.theme.LightGreen

@Preview(showBackground = true)
@Composable
fun AccountScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyListItemWithLeadIcon(
            icon = "\uD83C\uDFE0",
            iconBg = Color.White,
            content = {
                Text(text = "Баланс")
            },
            trailContent = {
                Text(text = "670 000 R")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(R.drawable.settings_trailing_element),
                    contentDescription = "Подробнее",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier
                .height(57.dp)
                .background(LightGreen)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Валюта")
            },
            trailContent = {
                Text(text = "RUB")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(R.drawable.settings_trailing_element),
                    contentDescription = "Подробнее",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier
                .height(57.dp)
                .background(LightGreen)

        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(233.dp)
                .background(color = DarkGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Здесь будет график", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



