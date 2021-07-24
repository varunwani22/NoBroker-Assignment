package com.dev.nbassignment.model


import com.google.gson.annotations.SerializedName

data class ResponseModelItem(
    @SerializedName("image")
    var image: String,
    @SerializedName("subTitle")
    var subTitle: String,
    @SerializedName("title")
    var title: String
)