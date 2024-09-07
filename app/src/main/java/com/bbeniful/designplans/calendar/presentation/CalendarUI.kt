package com.bbeniful.designplans.calendar.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bbeniful.designplans.calendar.presentation.components.CalendarItem
import com.bbeniful.designplans.calendar.presentation.components.DayView
import com.bbeniful.designplans.calendar.presentation.components.MonthHeader
import com.bbeniful.designplans.calendar.utilities.DateHelper

@Composable
fun CalendarUI(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<CalendarViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Column(modifier = modifier){
        MonthHeader(
            yearMonth = uiState.value.yearMonth,
            onPreviousMonthButtonClicked = { prev -> viewModel.toPreviousMonth(prev)},
            onNextMonthButtonClicked = { next -> viewModel.toNextMonth(next)}
        )
        Row {
            repeat(DateHelper.daysOfWeek.size) {
                val item = DateHelper.daysOfWeek[it]
                DayView(item, modifier = Modifier.weight(1f))
            }
        }
        CalendarUIContent(dates = uiState.value.dates) { date ->
            Toast.makeText(context, date.dayOfMonth, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun CalendarUIContent(
    dates: List<CalendarUiState.Date>,
    onDateClickListener: (CalendarUiState.Date) -> Unit,
) {
    Column {
        var index = 0
        repeat(6) {
            if (index >= dates.size) return@repeat
            Row {
                repeat(7) {
                    val item = if (index < dates.size) dates[index] else CalendarUiState.Date()
                    CalendarItem(
                        date = item,
                        onClickListener = onDateClickListener,
                        modifier = Modifier.weight(1f)
                    )
                    index++
                }
            }
        }
    }
}



