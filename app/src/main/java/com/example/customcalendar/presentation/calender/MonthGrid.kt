package com.example.customcalendar.presentation.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun MonthGrid(
    days: List<LocalDate?>,
    onDateSelected: (LocalDate) -> Unit
) {
    val isToday = remember {
        mutableStateOf(false)
    }
    val selectedDay = remember {
        mutableStateOf<LocalDate?>(null)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(days.size) { index ->
            val day = days[index]
            val today = LocalDate.now()
            isToday.value = day == today

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable(enabled = day != null) {
                        day?.let {
                            onDateSelected(it)
                            selectedDay.value = it
                        }
                    }
                    .padding(4.dp)
                    .background(
                        shape = CircleShape,
                        color = if (isToday.value) {
                            Color.Cyan
                        } else if (day != null && selectedDay.value == day) {
                            Color.Green
                        } else {
                            Color.Unspecified
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                day?.let {
                    Text(
                        text = it.dayOfMonth.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
