package com.bbeniful.designplans.navigationDrawer

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SingleNews(paddingValues: PaddingValues,title: String) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(text = title)
    }
}