package com.bbeniful.designplans.calendar.presentation

import java.time.YearMonth


data class CalendarUiState(
    val yearMonth: YearMonth = YearMonth.now(),
    val dates: List<Date> = emptyList()
) {
    data class Date(
        val dayOfMonth: String = "",
        val isSelected: Boolean = false
    )
}