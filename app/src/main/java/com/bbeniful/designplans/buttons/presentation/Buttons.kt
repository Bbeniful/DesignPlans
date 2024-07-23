package com.bbeniful.designplans.buttons.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.core.ui.theme.darkBlack
import com.bbeniful.designplans.core.ui.theme.darkGray
import com.bbeniful.designplans.core.ui.theme.darkGreen
import com.bbeniful.designplans.core.ui.theme.lightBlueNew
import com.bbeniful.designplans.core.ui.theme.orange
import com.bbeniful.designplans.core.ui.theme.purple

@Composable
fun Buttons() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DoubleColorButton(mainColor = darkBlack, secondColor = orange) {
            Text(text = "Hello")
        }

        WeirdShapeButton(colors = listOf(purple, darkGreen)) {
            Text(text = "Hello")
        }

        SeemsTransparentButton(lightBlueNew, secondColor = Color.LightGray) {
            Text(text = "Hello")
        }

        DigitalSeemsButton {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Hello",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = darkGray,
                textAlign = TextAlign.Start
            )
        }


    }
}