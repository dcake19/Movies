package com.example.android.movies.api.data.movie
import com.google.gson.annotations.SerializedName


data class MovieVideos(
		@SerializedName("id") val id: Int?,
		@SerializedName("results") val results: List<VideoResult>
)

data class VideoResult(
		@SerializedName("id") val id: String?,
		@SerializedName("iso_639_1") val iso6391: String?,
		@SerializedName("iso_3166_1") val iso31661: String?,
		@SerializedName("key") val key: String?,
		@SerializedName("name") val name: String?,
		@SerializedName("site") val site: String?,
		@SerializedName("size") val size: Int?,
		@SerializedName("type") val type: String?
)