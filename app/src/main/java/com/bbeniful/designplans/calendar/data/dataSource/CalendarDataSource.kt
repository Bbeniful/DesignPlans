package com.bbeniful.designplans.calendar.data.dataSource

import com.bbeniful.designplans.calendar.utilities.getDayOfMonthStartingFromMonday
import com.bbeniful.designplans.calendar.presentation.CalendarUiState
import java.time.LocalDate
import java.time.YearMonth


class CalendarDataSource {

    fun getDates(yearMonth: YearMonth): List<CalendarUiState.Date> {
        return yearMonth.getDayOfMonthStartingFromMonday()
            .map { date ->
                CalendarUiState.Date(
                    dayOfMonth = if (date.monthValue == yearMonth.monthValue) {
                        "${date.dayOfMonth}"
                    } else {
                        "" // Fill with empty string for days outside the current month
                    },
                    isSelected = date.isEqual(LocalDate.now()) && date.monthValue == yearMonth.monthValue
                )
            }
    }
}