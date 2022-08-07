package com.moeumi.client

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.moeumi.client.ui.theme.MoeumiTheme

class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoeumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ContentAppBar()
                        WebViewCompose()
                    }
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewCompose(url: String = "https://home.pen.go.kr/yeyak/edu/lib/selectEduInfo.do?mi=14556&eduSeq=3502&srchRsSysId=&srchEduCtgry=&currPage=1&srchRsvSttus=&srchPeriodDiv=rcept&srchRsvBgnde=&srchRsvEndde=&srchRsvValue=&pageIndex=50") {
    val state = rememberWebViewState(url)
    
    WebView(
        state,
        onCreated = { it.settings.javaScriptEnabled = true },
        captureBackPresses = false
    )
}
