package com.example.customcalendar.presentation.tasklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customcalendar.data.model.Task
import com.example.customcalendar.presentation.tasklist.dialog.DeleteTaskDialog

@Composable
fun TaskItem(task: Task, onDeleteClick: () -> Unit) {
    val shouldShowDeleteDialog = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { shouldShowDeleteDialog.value = true }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.taskDetail.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.taskDetail.description, style = MaterialTheme.typography.bodyMedium)
        }
    }

    if (shouldShowDeleteDialog.value) {
        DeleteTaskDialog(
            onDismiss = {
                shouldShowDeleteDialog.value = false
            },
            onDelete = {
                onDeleteClick()
            }
        )
    }
}
