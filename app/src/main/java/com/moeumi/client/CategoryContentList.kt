package com.moeumi.client

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moeumi.client.dummies.readyTo
import com.moeumi.client.ui.theme.MoeumiTheme
import com.moeumi.client.view_model.GetContentCategoryViewModel

class CategoryContentList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoeumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CategoryList(category = getIntent().getStringExtra("category"))
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryList(
    contentViewModel: GetContentCategoryViewModel = GetContentCategoryViewModel(),
    category: String? = ""
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = MainPadding * 2)
    ) {
        CategoryContentListAppBar()
        SelectCategoryContent()
        Divider(
            modifier = Modifier
                .alpha(0.5f)
                .padding(
                    top = MainPadding / 2,
                    bottom = MainPadding / 2,
                    start = MainPadding / 4,
                    end = MainPadding / 4
                )
        )
        if (category != "전체") {
            contentViewModel.getContent(parameter = "/category/${category}")
        } else {
            contentViewModel.getContent()
        }
        val contentList by contentViewModel.content.collectAsState()
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .height(900.dp)
                .padding(
                    top = MainPadding,
                    bottom = MainPadding,
                    start = MainPadding / 2,
                    end = MainPadding / 2
                ),
        ) {
            item {
                MainListTitle("${category}")
            }

            if (contentList.isNotEmpty()) {
                itemsIndexed(contentList) { index, item ->
                    var page = 2
                    kotlin.runCatching {
                        Content(
                            title = item.contents_title,
                            place = item.center_name,
                            date = item.apply_end_date,
                            url = item.detail_link
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    if (index == contentList.lastIndex) {
                        contentViewModel.getContent(
                            page,
                            parameter = "/category/${category}"
                        )
                        page += 1
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectCategoryContent() {
//    val selected = arrayListOf(true, false)
    var specificLocateSelected by rememberSaveable { mutableStateOf(false) }
    var allLocateSelected by rememberSaveable { mutableStateOf(true) }
    Row {
        CategoryContent(isSelected = specificLocateSelected, categoryText = "해운대구") {
            specificLocateSelected = !specificLocateSelected
            allLocateSelected = !allLocateSelected
        }
        CategoryContent(isSelected = allLocateSelected, categoryText = "전체") {
            specificLocateSelected = !specificLocateSelected
            allLocateSelected = !allLocateSelected
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedCrossfadeTargetStateParameter", "UseCheckPermission")
@Preview
@Composable
fun CategoryContent(isSelected: Boolean, categoryText: String, onChange: () -> Unit) {
    val context = LocalContext.current
    val backgroundColor: Color
    val textColor: Color
    if (isSelected) {
        backgroundColor = Color.White
        textColor = Color.Black
    } else {
        backgroundColor = Color.Black
        textColor = Color.White
    }

    AnimatedContent(isSelected) {
        OutlinedButton(
            onClick = {
                Toast.makeText(context, readyTo, Toast.LENGTH_SHORT).show()
//                onChange()
            },
            shape = RoundedCornerShape(11.dp),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
            modifier = Modifier
                .animateEnterExit(
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut()
                )
                .padding(8.dp)
                .height(68.dp)
                .width(164.8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = categoryText,
                    textAlign = TextAlign.Center,
                    color = textColor,
                    modifier = Modifier
                )
            }
        }
    }
}
