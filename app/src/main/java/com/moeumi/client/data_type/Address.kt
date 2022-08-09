package com.moeumi.client.data_type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//data class Address(
//    val data: Document
//)

@Serializable
data class Address(
    @SerialName("region_type")
    val regionType: String,

    val code: String,

    @SerialName("address_name")
    val addressName: String,

    @SerialName("region_1depth_name")
    val region1DepthName: String,

    @SerialName("region_2depth_name")
    val region2DepthName: String,

    @SerialName("region_3depth_name")
    val region3DepthName: String,

    @SerialName("region_4depth_name")
    val region4DepthName: String,

    val x: Double,
    val y: Double
)
