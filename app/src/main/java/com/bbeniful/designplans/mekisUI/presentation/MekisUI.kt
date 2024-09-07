package com.bbeniful.designplans.mekisUI.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.R
import com.bbeniful.designplans.core.ui.theme.dirtyWhite
import com.bbeniful.designplans.mekisUI.presentation.components.ButtonsWithIcon
import com.bbeniful.designplans.mekisUI.presentation.components.InfoBox
import com.bbeniful.designplans.mekisUI.presentation.components.LinksUI
import com.bbeniful.designplans.mekisUI.presentation.components.OtherLinks
import com.bbeniful.designplans.mekisUI.presentation.components.YellowBox

@Composable
fun MekisUI(navToAnimView: () -> Unit = {}) {
    MekiContent(navToAnimView = navToAnimView)
}

@Composable
fun MekiContent(navToAnimView: () -> Unit = {}) {

    var showDialog by remember { mutableStateOf(false) }
    var buttonPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dirtyWhite)
            .padding(bottom = 50.dp)
    ) {
        if (showDialog) {
            InfoBox(onDismiss = {
                showDialog = false
            }, position = buttonPosition)
        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            Column(Modifier.fillMaxSize()) {
                Header(navToAnimView = navToAnimView)
                Spacer(modifier = Modifier.weight(1f))
                YellowBox {
                    Column {
                        BoxContent(
                            infoBoxClick = {
                                buttonPosition = it
                                showDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QRcode() {
    Box(
        modifier = Modifier
            .size(160.dp)
            .padding(bottom = 12.dp)
            .background(color = Color.White, shape = RoundedCornerShape(22.dp))
    ) {
        Icon(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.example_qr),
            contentDescription = "",
            tint = Color.Black
        )

    }
}

@Composable
fun BoxContent(infoBoxClick: (Offset) -> Unit = {}) {

    var buttonPosition by remember { mutableStateOf(Offset.Zero) }

    Box {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp),
                    text = "This is your code",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(0.6f))
                Icon(
                    modifier = Modifier
                        .padding(end = 22.dp, top = 10.dp)
                        .onGloballyPositioned { coordinates ->
                            // Get the button's position on the screen
                            val position = coordinates.positionInWindow()
                            buttonPosition = position
                        }
                        .clickable {
                            infoBoxClick(buttonPosition)
                        },
                    imageVector = Icons.Default.Info,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
            Text(text = "Show this fucking code", color = Color.Black)
            Text(
                color = Color.Black,
                text = "59 69",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider()
            Text(
                text = "This is your QR Code",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            QRcode()
        }
    }


}

@Composable
fun Header(navToAnimView: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.Black,
            text = "Hello Meki UI",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(22.dp))
        ButtonsWithIcon(
            modifier = Modifier.width(300.dp),
            text = "Hello this is a bit longer",
            onClick = navToAnimView
        )
        Spacer(modifier = Modifier.height(22.dp))
        ButtonsWithIcon(
            modifier = Modifier.width(300.dp),
            text = "Hello, I'm smaller"
        )
        Spacer(modifier = Modifier.height(22.dp))
        OtherLinks()

    }
}

@Preview
@Composable
fun MekisUIPreview() {
    MekisUI()
}
