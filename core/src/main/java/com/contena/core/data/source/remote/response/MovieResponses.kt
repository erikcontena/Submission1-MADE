package com.contena.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponses(

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)
