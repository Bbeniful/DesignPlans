package com.bbeniful.designplans.clickableText.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClickableTextUI(
    onTextClick: () -> Unit,
    onTextClickSecond: () -> Unit,
    onClickWithText: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ClickableTextContent(
            onTextClick = onTextClick,
            onTextClickSecond = onTextClickSecond,
            onClickWithText = onClickWithText,
            texts = mapOf(
                "apple" to "Apple",
                "orange" to "Orange"
            )
        )
    }
}

@Composable
fun ClickableTextContent(
    texts: Map<String, String> = mapOf(),
    onTextClick: () -> Unit,
    onTextClickSecond: () -> Unit,
    onClickWithText: (String) -> Unit = {}
) {
    val context = LocalContext.current
    // Display a link in the text and log metrics whenever user clicks on it. In that case we handle
    // the link using openUri method of the LocalUriHandler

    val myClickableText = buildAnnotatedString {
        append("Build better apps faster with ")
        val link =
            LinkAnnotation.Url(
                "https://developer.android.com/jetpack/compose",
                TextLinkStyles(SpanStyle(color = Color.Blue))
            ) {
                onTextClick()
            }
        withLink(link) { append("Jetpack Compose") }
        append("!")
        withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
            val linnktwo = LinkAnnotation.Url("") {
                onTextClickSecond()
            }
            withLink(linnktwo) { append(" Learn more!\n") }
        }
        append("This is a new approach")
        withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)){
            val fromMap = LinkAnnotation.Clickable("") {
                onClickWithText(texts["apple"] ?: "")
            }
            withLink(fromMap) {append("Map text: ${texts["apple"]}")}
        }
    }
    Text(text = myClickableText)
}


@Preview
@Composable
fun ClickableTextUIPreview() {
    ClickableTextContent(onTextClick = {}, onTextClickSecond = {})
}