package com.example.shmrapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.models.ArticleModel

@Composable
fun ArticleItem(article: ArticleModel) {
    ListItem(
        lead = {
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.green_light),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(3.dp)
            ) {
                Text(text = article.icon)
            }
        },
        content = {
            Text(
                text = article.label,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}
