import com.moeumi.client.data_type.ContentElement

const val getContentUrl =
    "https://mwwneja7pl.execute-api.ap-northeast-2.amazonaws.com/dev/contents"

const val getCurrentDistrictUrl =
    "https://mwwneja7pl.execute-api.ap-northeast-2.amazonaws.com/dev/get_district"

val contentDummies = ContentElement(
    center_name = "더미데이터",
    placement_name = "우동",
    contents_title = "우동",
    contents_id = 1,
    category = "우동",
    detail_link = "우동",
    apply_start_date = "우동",
    apply_end_date = "우동",
    operate_start_date = "우동",
    operate_end_date = "우동",
    edu_target = "우동",
    apply_target = "우동",
    max_apply_num = 1,
    applied_num = 1,
    wait_num = 1,
    apply_state = "우동"
)

const val readyTo = "준비중이에요"