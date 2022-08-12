package com.moeumi.client.data_type


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ContentData = List<ContentElement>

@Serializable
data class ContentElement(
    @SerialName("center_name")
    val center_name: String,

    @SerialName("placement_name")
    val placement_name: String,

    @SerialName("contents_title")
    val contents_title: String,

    @SerialName("contents_id")
    val contents_id: Long,

    val category: String,

    @SerialName("detail_link")
    val detail_link: String,

    @SerialName("apply_start_date")
    val apply_start_date: String,

    @SerialName("apply_end_date")
    val apply_end_date: String,

    @SerialName("operate_start_date")
    val operate_start_date: String,

    @SerialName("operate_end_date")
    val operate_end_date: String,

    @SerialName("edu_target")
    val edu_target: String,

    @SerialName("apply_target")
    val apply_target: String,

    @SerialName("max_apply_num")
    val max_apply_num: Long,

    @SerialName("applied_num")
    val applied_num: Long,

    @SerialName("wait_num")
    val wait_num: Long,

    @SerialName("apply_state")
    val apply_state: String
)
