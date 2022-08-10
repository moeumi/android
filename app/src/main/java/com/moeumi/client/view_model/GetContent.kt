package com.moeumi.client.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.moeumi.client.data_type.ContentData
import com.moeumi.client.data_type.ContentElement
import contentDummies
import getContentUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class GetContentViewModel : ViewModel() {
    private val _content =
        MutableStateFlow(listOf(contentDummies))
    val content = _content.asStateFlow()

    private fun setContent(content: ContentData) {
        CoroutineScope(Dispatchers.Main).launch {
            _content.value = content
        }
    }

    private fun addContent(content: ContentData) {
        CoroutineScope(Dispatchers.Main).launch {
            _content.value = _content.value.plus(content)
        }
    }

    fun getContent(page: Int = 1, parameter: String? = "") {
        val url = "$getContentUrl$parameter?page=$page"
        val gson = Gson()
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(url).get()
            val body = doc.select("body").text()
            val data: ContentData =
                gson.fromJson(body, Array<ContentElement>::class.java).toList()
            if (page == 1) {
                setContent(data)
            } else {
//                delay(1000)
//                addContent(data)
            }
            Log.d("getContent", data.toString())
            Log.d("getContent", url)
        }
    }
}
