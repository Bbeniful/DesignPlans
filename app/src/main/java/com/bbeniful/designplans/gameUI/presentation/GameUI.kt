package com.bbeniful.designplans.gameUI.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.designplans.R
import com.bbeniful.designplans.core.ui.theme.btnGreen
import com.bbeniful.designplans.core.ui.theme.darkBlue
import com.bbeniful.designplans.core.ui.theme.darkGrayGame
import com.bbeniful.designplans.core.ui.theme.darkGrayIcon
import com.bbeniful.designplans.core.ui.theme.darkGrayTwo

enum class Pages {
    About,AddOns
}


@Composable
fun GameUI(){
    Box{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Header()
            GameBanner()
            GameDetails() {

            }
            Spacer(modifier = Modifier.padding(top = 12.dp))
            PagerDetails {
                GameDetailsState(page = Pages.About)
            }

        }
        BottomBar(
            modifier = Modifier.align(
                Alignment.BottomCenter
            )
        )
    }
}

@Composable
fun GameDetailsState(page: Pages){
    when(page) {
        Pages.About -> {
            AboutContent()
        }
        Pages.AddOns -> {
            AddOnsContent()
        }

    }
}

@Composable
fun Header(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(
                darkBlue,
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
        Icon(imageVector = Icons.Default.Email, contentDescription = "", tint = Color.White)
    }

}

@Composable
fun GameBanner(){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(12.dp)),
        painter = painterResource(id = R.drawable.img_1),
        contentScale = ContentScale.FillWidth,
        contentDescription = "")

}

@Composable
fun GameDetails(onTextClick: (SelectedPage) -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp)
            .background(color = darkGrayGame, shape = RoundedCornerShape(12.dp))
    ){
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ){
                        Text(text = "Black Myth: Wukong", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Text too small, but I try to add the same length", color = Color.Gray, fontSize = 12.sp)
                    }
                    Icon(
                        modifier = Modifier
                            .padding(top = 10.dp, end = 22.dp)
                            .size(40.dp),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "",
                        tint = darkGrayIcon
                    )
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            AboutGame(
                pages = listOf("About", "Add-Ons"),
                onTextClick = onTextClick)

        }
    }

}

typealias SelectedPage = String

@Composable
fun AboutGame(
    modifier: Modifier = Modifier,
    pages: List<String>,
    onTextClick: (SelectedPage) -> Unit
){


    var isClicked by remember {
        mutableStateOf(false)
    }

    var clickedItem by remember {
        mutableStateOf(-1)
    }

    LazyRow(
        modifier = modifier.padding(start = 10.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(pages) { index, text ->
            Column(
                modifier = Modifier.clickable {
                    clickedItem = if (clickedItem == index) -1 else index
                    onTextClick(text)
                }
            ) {
                Text(
                    text = pages[index],
                    color = if (clickedItem == index) Color.White else Color.DarkGray,
                    fontWeight = if (clickedItem == index) FontWeight.Bold else FontWeight.Normal
                )
                if (clickedItem == index) {
                    Box(modifier = Modifier
                        .width(20.dp)
                        .height(2.dp)
                        .background(Color.Blue))
                }
            }
        }
    }
}

@Composable
fun PagerDetails(content: @Composable () -> Unit){
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun AboutContent(){
    val items = listOf("valamiasdasdasdas", "valami2", "valami3")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { text ->
                Genre(data = text)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(items) { text ->
                Requirements(name = text)

            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "qwkeughwquikrfgbwekuirfgtekujhtgfbvjerkhwgtjhergbtujkhwegvbrfjuwegvbtrujkwegbvtjhwegbrjhwegbjrthwegvbjrhgvbwejhrgvbjwehrgvjhwe" +
                "qwkejgjhkwqgejhqwgejhgqwjehgwqjhegjqwqweqweqwedqwohrfuiwegbrfiqwugbeiwuqgbrjikuqwgbhrjkuqwgbrjhqwgbrjkhqwgrkhgejkwhqgekjhwqgjkeqw" +
                "eqw" +
                "eqweqweeeeeeeeeeeeeeeeeqwrqwrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrqwrqwrrrrrrrrrrrrr" +
                "qwrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrjh", color = Color.White)
    }
}

@Composable
fun AddOnsContent(){
    val items = listOf("valamiasdasdasdas", "valami2", "valami3")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { text ->
                Genre(data = text)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(items) { text ->
                Requirements(name = text)

            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "qwkeughwquikrfgbwekuirfgtekujhtgfbvjerkhwgtjhergbtujkhwegvbrfjuwegvbtrujkwegbvtjhwegbrjhwegbjrthwegvbjrhgvbwejhrgvbjwehrgvjhwe" +
                "qwkejgjhkwqgejhqwgejhgqwjehgwqjhegjqwqweqweqwedqwohrfuiwegbrfiqwugbeiwuqgbrjikuqwgbhrjkuqwgbrjhqwgbrjkhqwgrkhgejkwhqgekjhwqgjkeqw" +
                "eqw" +
                "eqweqweeeeeeeeeeeeeeeeeqwrqwrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrqwrqwrrrrrrrrrrrrr" +
                "qwrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrgrjh", color = Color.White)
    }
}

@Composable
fun Genre(data: String){
    Box(
        modifier = Modifier
            .height(30.dp)
            .background(color = darkGrayTwo, shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier.padding(5.dp),
            text = data, fontSize = 12.sp, color = Color.White)
    }
}

@Composable
fun Requirements(name: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "", tint = Color.White)
        Text(text = name, color = Color.White)

    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = darkGrayGame,
                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 10.dp)
            )
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 35.dp, vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = btnGreen
            ),
            onClick = { /*TODO*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "", tint = Color.White)
                Text(text = "Downloading")
            }
        }
    }
}

@Preview
@Composable
fun PreviewOfGame(){
    GameUI()
}