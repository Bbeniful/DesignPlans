package com.bbeniful.designplans.mekisUI.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.R

@Composable
fun ButtonsWithIcon(modifier: Modifier = Modifier, onClick: () -> Unit = {}, text: String) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp, vertical = 5.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.cute_flower),
            contentDescription = "",
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text, fontSize = 18.sp,color = Color.Black)
    }

}


@Preview
@Composable
fun PreviewButtonsWithIcon() {
    ButtonsWithIcon(text = "almafa ahjaahahj")
}