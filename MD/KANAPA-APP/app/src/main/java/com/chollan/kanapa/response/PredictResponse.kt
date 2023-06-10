package com.chollan.kanapa.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PredictResponse(

	@field:SerializedName("probability")
	val probability: Float,

	@field:SerializedName("class")
	val predict: String
) : Parcelable
