package com.moeumi.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moeumi.client.ui.theme.MoeumiTheme

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
                    CategoryList()
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryList() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        CategoryContentListAppBar()
        SelectCategoryContent()
        for (i in 0 until 10) {
            Content()
        }
    }
}

@Preview
@Composable
fun SelectCategoryContent() {
    val selected = rememberSaveable { listOf(true, false) }
    Row {
        CategoryContent(selected[0])
        CategoryContent(selected[1])
    }
}

@Preview
@Composable
fun CategoryContent(isSelected: Boolean = false) {
    val backgroundColor: Color
    val textColor: Color
    if (isSelected) {
        backgroundColor = Color.White
        textColor = Color.Black
    } else {
        backgroundColor = Color.Black
        textColor = Color.White
    }

    OutlinedButton(
        onClick = {
            //TODO()//
        },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .padding(8.dp)
            .height(68.dp)
            .width((273 / 1.8).dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "해운대구",
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier
            )
        }
    }
}
