package com.moeumi.client.dummies

import com.moeumi.client.data_type.ContentElement

const val apiUrl = "https://mwwneja7pl.execute-api.ap-northeast-2.amazonaws.com/dev/"
const val getContentUrl = "${apiUrl}contents"
const val getCurrentDistrictUrl = "${apiUrl}get_district"

val contentDummies = ContentElement(
    center_name = "더미데이터",
    placement_name = "더미데이터",
    contents_title = "더미데이터",
    contents_id = 1,
    category = "더미데이터",
    detail_link = "더미데이터",
    apply_start_date = "더미데이터",
    apply_end_date = "더미데이터",
    operate_start_date = "더미데이터",
    operate_end_date = "더미데이터",
    edu_target = "더미데이터",
    apply_target = "더미데이터",
    max_apply_num = 1,
    applied_num = 1,
    wait_num = 1,
    apply_state = "더미데이터"
)

const val readyTo = "준비중이에요"