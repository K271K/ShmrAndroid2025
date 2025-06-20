package com.example.shmrapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyListItem(
    modifier: Modifier = Modifier,
    leadContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit = {},
    trailContent: @Composable () -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = if (onClick == null) false else true,
                onClick = {
                    onClick?.invoke()
                }
            )
            .then(modifier)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadContent?.let {
            it()
            Spacer(modifier = Modifier.width(16.dp))
        }
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
        Spacer(modifier = Modifier.width(16.dp))
        trailContent()
    }
}

@Composable
fun MyListItemWithLeadIcon(
    icon: String,
    iconBg: Color,
    content: @Composable () -> Unit,
    trailContent: @Composable () -> Unit,
    modifier: Modifier,
    onClick: (() -> Unit)? = null
) {
    MyListItem(
        modifier = modifier,
        leadContent = {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(iconBg)
            ) {
                Text(text = icon, modifier = Modifier.align(Alignment.Center))
            }
        },
        content = {
            content()
        },
        trailContent = {
            trailContent()
        },
        onClick = onClick
    )
}

@Composable
fun MyListItemOnlyText(
    modifier: Modifier,
    content: @Composable () -> Unit,
    trailContent: @Composable () -> Unit,
    onClick: (() -> Unit)? = null
) {
    MyListItem(
        modifier = modifier,
        leadContent = null,
        content = {
            content()
        },
        trailContent = {
            trailContent()
        },
        onClick = onClick
    )
}