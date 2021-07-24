package com.dev.nbassignment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseModel(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("subTitle")
	val subTitle: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)