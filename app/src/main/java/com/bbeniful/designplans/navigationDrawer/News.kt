package com.bbeniful.designplans.navigationDrawer

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

enum class NewsType(private val typeNum: Int) {
    Normal(0),
    ListWithNum(1),
    Paid(2);

    val type = typeNum
}

@Serializable
@Parcelize
data class News(
    val title: String,
    val image: String,
    val author: String,
    val type: NewsType,
    val content: String,
    val category: String,
    val date: String
) : Parcelable

val list = (1..8).map {
    News(
        title = "Title $it",
        image = if (it < 4) "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg" else "",
        author = "Joska",
        type = if (it < 7) NewsType.Normal else NewsType.ListWithNum,
        content = "lotremipsum hahahaha",
        category = "Category",
        date = "2024.01.01"
    )
}


@Composable
fun NewsList(news: List<News>, onNewsClick: (News) -> Unit = {}) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val groupedNews = news.groupBy { it.type }
        groupedNews.forEach { (type, groupedNewsList) ->
            itemsIndexed(groupedNewsList) { index, newsItem ->
                val displayIndex = if (type == NewsType.ListWithNum) index + 1 else index
                NewsItemState(index = displayIndex, item = newsItem, onNewsClick = onNewsClick)
            }
        }
    }
}

@Composable
private fun NewsItemState(index: Int, item: News, onNewsClick: (News) -> Unit = {}) {
    when (item.type) {
        NewsType.Normal -> {
            NewsItemNormal(news = item, onNewsClick = onNewsClick)
        }
        NewsType.ListWithNum -> {
            NewsItemWithIndex(index = index, news = item, onNewsClick = onNewsClick)
        }
        NewsType.Paid -> {
            NewsItemPaid(news = item, onNewsClick = onNewsClick)
        }
    }
    Spacer(Modifier.height(20.dp))
}


@Composable
fun NewsItemNormal(news: News, onNewsClick: (News) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onNewsClick(news) }
    ) {
        if (news.image.isNotEmpty()) {
            Box(modifier = Modifier.height(211.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .aspectRatio(1.776f),
                    model = news.image,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Category(
                    modifier = Modifier.align(
                        alignment = Alignment.BottomStart
                    ),
                    category = news.category
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "${news.author.uppercase()} - ${news.date}")
        Text(text = news.title, fontSize = 25.sp, fontWeight = FontWeight(600))
        if (news.content.isNotEmpty()) {
            Text(text = news.content)
        }
    }

}

@Composable
fun Category(modifier: Modifier = Modifier, category: String) {
    val color = Color(0xFFF13E3A)
    Box(
        modifier = modifier
            .padding(15.dp)
            .background(color = color)
            .padding(5.dp)

    ) {
        Text(text = category.uppercase(), color = Color.White)
    }
}


@Composable
fun NewsItemPaid(news: News, onNewsClick: (News) -> Unit = {}) {

}

@Composable
fun NewsItemWithIndex(index: Int, news: News, onNewsClick: (News) -> Unit = {}) {

    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .padding(15.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp)
                    .clickable { onNewsClick(news) },
                contentAlignment = Alignment.Center

            ) { Text("$index") }
            Column {
                Row {
                    Text(news.category)
                    Text(news.author)
                }
                Text(news.title, fontWeight = FontWeight.Bold)
            }
        }
    }

}

@Preview
@Composable
fun NewsItemPreview() {
    NewsItemNormal(
        news = list.first()
    )
}