package com.example.customcalendar.presentation.calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.customcalendar.R
import com.example.customcalendar.constants.Constants
import com.example.customcalendar.presentation.calender.dialog.AddTaskDialog
import com.example.customcalendar.presentation.calender.event.CalendarEvent
import com.example.customcalendar.presentation.calender.viewmodel.CalendarViewModel
import com.example.customcalendar.presentation.util.WeekDay
import java.time.LocalDate

@Composable
fun CalendarScreen(
    navController: NavHostController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Top - Year and Month selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { viewModel.onEvent(CalendarEvent.PreviousMonth) }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.prev_month)
                    )
                }

                Text(
                    text = "${state.selectedMonth.name} ${state.selectedYear}"
                )

                IconButton(
                    onClick = { viewModel.onEvent(CalendarEvent.NextMonth) }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = stringResource(R.string.nxt_month)
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Weekdays
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                WeekDay.entries.forEach { day ->
                    Text(text = day.day, style = MaterialTheme.typography.titleSmall)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Month grid
            MonthGrid(
                days = state.daysInMonth,
                onDateSelected = { date ->
                    selectedDate = date
                    showAddDialog = true
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Constants.NAV_DEST_TASKS) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.view_tasks))
            }
        }
    }

    if (showAddDialog && selectedDate != null) {
        AddTaskDialog(
            selectedDate = selectedDate!!,
            onDismiss = { showAddDialog = false },
            onSave = { title, description ->
                viewModel.onEvent(CalendarEvent.AddTask(title, description))
                showAddDialog = false
            }
        )
    }
}
