package com.bbeniful.designplans.designList.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.designList.utilities.Designs
import com.bbeniful.designplans.designList.utilities.designs

@Composable
fun DesignListUI(onItemClick: (Any) -> Unit) {
    DesignListContent(designs = designs, onItemClick = onItemClick)
}

@Composable
fun DesignListContent(designs: List<Designs>, onItemClick: (Any) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            itemsIndexed(designs) { index, design ->
                Text(
                    text = "${index + 1}. ${design.design.name}",
                    modifier = Modifier
                        .clickable {
                            onItemClick(design.path)
                        }
                        .padding(start = 12.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}