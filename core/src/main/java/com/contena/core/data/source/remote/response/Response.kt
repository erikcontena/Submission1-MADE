package com.contena.core.data.source.remote.response

import com.google.gson.annotations.SerializedName



data class Response<T>(

	@field:SerializedName("results")
	val results: List<T>

)