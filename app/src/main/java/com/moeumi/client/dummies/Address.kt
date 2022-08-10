import com.moeumi.client.data_type.Address
import com.moeumi.client.data_type.ContentElement

val addressDummies = Address(
    regionType = "B",
    code = "2635010500",
    addressName = "부산광역시 해운대구 우동",
    region1DepthName = "부산광역시",
    region2DepthName = "해운대구",
    region3DepthName = "우동",
    region4DepthName = "",
    x = 129.14378876810525,
    y = 35.172340282874565
)

const val getContentUrl =
    "https://mwwneja7pl.execute-api.ap-northeast-2.amazonaws.com/dev/contents"

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