package com.bbeniful.designplans.navigationDrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.core.ui.theme.drawerMenu
import com.bbeniful.designplans.core.ui.theme.drawerMenuBackground


@Composable
fun DrawersMenu(onClose: () -> Unit = {}, onItemClick: (String) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(drawerMenuBackground),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier.height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 22.dp)
                    .clickable { onClose() }
                    .size(45.dp),
                imageVector = Icons.Default.Close,
                tint = Color.White,
                contentDescription = "Menu"
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 52.dp, bottom = 80.dp)
                .background(
                    color = drawerMenu,
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                )
                .padding(start = 25.dp, top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            (1..7).forEach {
                Text(
                    modifier = Modifier.clickable { onItemClick("Item $it") },
                    text = "Item $it",
                    color = Color.White,
                    fontSize = 22.sp,

                )
            }
            Spacer(Modifier.height(20.dp))
            HorizontalDivider(modifier = Modifier.padding(end = 40.dp))
            Spacer(Modifier.height(20.dp))
            (1..5).forEach {
                Text(
                    modifier = Modifier.clickable { onItemClick("Item $it") },
                    text = "Item $it",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDrawersMenu() {
    DrawersMenu()
}
