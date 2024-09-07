package com.bbeniful.designplans.mekisUI.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LinksUI() {
    val links = listOf(
        MyLink(
            userText = "Google",
            url = "www.google.com"
        ),
        MyLink(
            userText = "Facebook",
            url = "www.facebook.com"
        )
    )
    LinksContent(links = links)

}

@Composable
fun LinksContent(links: List<MyLink>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(links) { link ->
            LinkItem(myLink = link)
        }
    }

}

@Composable
fun OtherLinks() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinkItem(
            myLink = MyLink(
                userText = "Google",
                url = "https://www.google.com"
            )
        )
        VerticalDivider(
            modifier = Modifier
                .height(20.dp),
            color = Color.Blue
        )
        LinkItem(
            myLink = MyLink(
                userText = "Facebook",
                url = "https://www.facebook.com"
            )
        )

    }
}

@Composable
fun LinkItem(myLink: MyLink) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            buildAnnotatedString {
                val link =
                    LinkAnnotation.Url(
                        myLink.url,
                        TextLinkStyles(SpanStyle(color = Color.Blue))
                    ) {
                        val url = (it as LinkAnnotation.Url).url
                        // log some metrics
                        uriHandler.openUri(url)
                    }
                withLink(link) { append(myLink.userText) }
            },
            fontSize = 18.sp,
            style = TextStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        )

    }
}

data class MyLink(
    val userText: String,
    val url: String
)

@Preview
@Composable
fun LinksPreview() {
    LinksUI()
}