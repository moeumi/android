package com.moeumi.client.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.moeumi.client.data.data_type.ContentData
import com.moeumi.client.data.data_type.ContentElement
import com.moeumi.client.dummies.contentDummies
import com.moeumi.client.dummies.getContentUrl
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
    private val _isEnd = MutableStateFlow(false)
    val isEnd = _isEnd.asStateFlow()

    private fun setContent(content: ContentData) {
        _content.value = content
    }

    private fun addContent(content: ContentData) {
        _content.value = _content.value.plus(content)
    }

    fun getContent(page: Int = 1, parameter: String? = "") {
        val url = "$getContentUrl$parameter?page=$page"
        val gson = Gson()
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                if (!_isEnd.value) {
                    val doc = Jsoup.connect(url).get()
                    val body = doc.select("body").text()
                    val data: ContentData =
                        gson.fromJson(body, Array<ContentElement>::class.java).toList()
                    if (page == 1) {
                        setContent(data)
                    } else if (page > 1) {
                        addContent(data)
                    }
                    Log.d("getContent", "${data[0]}")
                }
            }.onSuccess {
                Log.d("getContent", "Surcess $url")
                Log.d("getContent", "Page $page")
            }.onFailure {
//                _isEnd.value = true
                Log.d("getContent", "last")
            }
        }
    }
}
