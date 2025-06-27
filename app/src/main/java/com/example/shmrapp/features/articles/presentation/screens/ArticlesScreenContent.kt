package com.example.shmrapp.features.articles.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.core.ui.components.MyListItemWithLeadIcon
import com.example.shmrapp.theme.LightGreen
import com.example.shmrapp.features.account.presentation.viewmodels.ArticlesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArticlesScreenContent(){
    val articlesViewModel = koinViewModel<ArticlesViewModel>()
    val state by articlesViewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = {Text("Найти статью")},
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.gray_light)),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Find an article"
                )
            }
        )
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
                LazyColumn {
                    items (state.articles) { article ->
                        MyListItemWithLeadIcon(
                            modifier = Modifier.height(70.dp),
                            icon = article.emoji,
                            iconBg = LightGreen,
                            content = {
                                Column {
                                    Text(text = article.name)
                                }
                            },
                            trailContent = {}
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}