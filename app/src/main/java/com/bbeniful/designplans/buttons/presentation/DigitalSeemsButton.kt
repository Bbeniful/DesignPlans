package com.bbeniful.designplans.buttons.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.core.ui.theme.darkGray
import com.bbeniful.designplans.core.ui.theme.digitalGreen
import com.bbeniful.designplans.core.ui.theme.red

@Composable
fun DigitalSeemsButton(onClick: () -> Unit = {}, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier.clickable {
            onClick()
        },
        contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(width = 240.dp, height = 90.dp)
                .background(
                    red, shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 20.dp
                    )
                )
        )
        Box(
            modifier = Modifier
                .size(width = 220.dp, height = 70.dp)
                .background(
                    darkGray, shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 20.dp
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(width = 190.dp, height = 50.dp)
                .background(
                    digitalGreen, shape = RoundedCornerShape(
                        bottomEnd = 20.dp
                    )
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

}


@Preview
@Composable
fun DigitalSeemsButtonPreview() {
    DigitalSeemsButton {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Almafa",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = darkGray,
            textAlign = TextAlign.Start
        )
    }

}