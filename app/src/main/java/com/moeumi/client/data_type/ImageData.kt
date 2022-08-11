package com.moeumi.client.data_type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ImageData = List<ImageElement>

@Serializable
data class ImageElement(
    @SerialName("image_link")
    val image_link: String,

)

