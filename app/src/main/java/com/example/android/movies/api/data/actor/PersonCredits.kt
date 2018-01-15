package com.example.android.movies.api.data.actor

import com.google.gson.annotations.SerializedName

data class PersonCredits(
        @SerializedName("cast") val cast: List<Cast?>?,
        @SerializedName("crew") val crew: List<Crew?>?,
        @SerializedName("id") val id: Int?
)

data class Cast(
        @SerializedName("character") val character: String?,
        @SerializedName("credit_id") val creditId: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("vote_count") val voteCount: Int?,
        @SerializedName("video") val video: Boolean?,
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("vote_average") val voteAverage: Double?,
        @SerializedName("title") val title: String?,
        @SerializedName("genre_ids") val genreIds: List<Int?>?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("id") val id: Int?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("poster_path") val posterPath: String?
)

data class Crew(
        @SerializedName("id") val id: Int?,
        @SerializedName("department") val department: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("job") val job: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("vote_count") val voteCount: Int?,
        @SerializedName("video") val video: Boolean?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("genre_ids") val genreIds: List<Int?>?,
        @SerializedName("vote_average") val voteAverage: Double?,
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("credit_id") val creditId: String?
)