package com.bbeniful.designplans.navigationDrawer

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.bbeniful.designplans.R
import com.bbeniful.designplans.core.ui.theme.drawerMenuBackground
import kotlinx.coroutines.launch


@Composable
fun NavigationDrawer(paddingValues: PaddingValues, onItemClick: (News) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentLayoutDirection = LocalLayoutDirection.current

    val items =
        listOf(
            Icons.Default.AccountCircle,
            Icons.Default.Email,
            Icons.Default.Favorite
        )
    val selectedItem = remember { mutableStateOf(items[0]) }
    val context = LocalContext.current

    var filter = remember {
        mutableStateOf("")
    }

    DirectionLayout(direction = LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            drawerState = drawerState,
            drawerContent = {
                Box {
                    ModalDrawerSheet(
                        drawerContainerColor = drawerMenuBackground
                    ) {
                        DirectionLayout(direction = currentLayoutDirection) {
                            //Column(Modifier.verticalScroll(rememberScrollState())) {
                            /* Spacer(Modifier.height(12.dp))
                             items.forEach { item ->
                                 NavigationDrawerItem(
                                     icon = { Icon(item, contentDescription = null) },
                                     label = { Text(item.name.substringAfterLast(".")) },
                                     selected = item == selectedItem.value,
                                     onClick = {
                                         scope.launch { drawerState.close() }
                                         selectedItem.value = item
                                     },
                                     modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                 )
                             }*/
                            DrawersMenu(
                                onClose = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    filter.value = ""
                                },
                                onItemClick = { item ->
                                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                    filter.value = item.filter { it.isDigit() }
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
                            )

                        }

                    }
                }


            },
            content = {
                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 12.dp, start = 22.dp)
                                    .requiredSize(28.dp)
                                    .align(Alignment.CenterStart)
                                    .clickable {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    },
                                painter = painterResource(R.drawable.menu_icon),
                                contentDescription = "Menu"
                            )
                            Text(
                                text = "Modal Navigation Drawer",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                ) { innerPadding ->
                    DirectionLayout(direction = currentLayoutDirection) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(top = 22.dp, start = 12.dp, end = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            NewsList(
                                news = list.filter { it.title.contains(filter.value) },
                                onNewsClick = onItemClick
                            )
                            /* Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                             Text(
                                 text = "Selected item is: ${
                                     selectedItem.value.name.substringAfterLast(
                                         "."
                                     )
                                 }"
                             )
                             Spacer(Modifier.height(20.dp))
                             Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }*/
                        }
                    }

                }
            }
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewOf() {
    NavigationDrawer(
        paddingValues =
        PaddingValues(
            top = 50.dp,
            bottom = 20.dp
        ),
        onItemClick =  {}
    )
}


@Composable
fun DirectionLayout(
    direction: LayoutDirection,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        content()
    }
}
