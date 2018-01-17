package com.example.android.movies.api

import com.example.android.movies.api.data.actor.PersonCredits
import com.example.android.movies.api.data.actor.PersonInfo
import com.example.android.movies.api.data.actor.PersonResults
import com.example.android.movies.api.data.movie.MovieCredits
import com.example.android.movies.api.data.movie.MovieInfo
import com.example.android.movies.api.data.movie.MovieResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    // results obtained from a search or discover
    @GET("/3/discover/movie")
    fun getDiscoverResults(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            // popularity.desc, first_air_date.desc, vote_average.desc
            @Query("sort_by") sortBy: String,
            // entered as comma separated integers e.g. 18,80
            @Query("with_genres") withGenres: String,
            @Query("without_genres") withoutGenres: String,
            // 0.0 to 10.0
            @Query("vote_average.gte") minVoteAverage: String,
            // greater than 0
            @Query("vote_count.gte") minVoteCount: String,
            // enter the year
            @Query("primary_release_year") primaryReleaseYear: String,
            // min runtime
            @Query("with_runtime.gte") minRuntime: String,
            // max runtime
            @Query("with_runtime.lte") maxRuntime: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    @GET("/3/search/movie")
    fun getSearchResults(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("query") query: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    @GET("/3/movie/top_rated")
    fun getTopRatedResults(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    @GET("/3/movie/upcoming")
    fun getUpcomingResults(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    @GET("/3/movie/now_playing")
    fun getNowPlayingResults(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    @GET("/3/movie/popular")
    fun getPopular(
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<MovieResults>

    // detailed movie info
    @GET("/3/movie/{id}")
    fun getMovieInfo(
            @Path(value = "id", encoded = true) movieId: String,
            // api key in gradle file
            @Query("api_key") apiKey: String): Observable<MovieInfo>

    //credits for a movie
    @GET("/3/movie/{id}/credits")
    fun getMovieCredits(
            @Path(value = "id", encoded = true) movieId: String,
            // api key in gradle file
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<MovieCredits>

    @GET("3/person/{person_id}")
    fun gePersonInfo(
            @Path(value = "id", encoded = true) actorId: String,
            @Query("api_key") apiKey: String): Observable<PersonInfo>

    @GET("3/person/{person_id}/movie_credits")
    fun getActorCredits(
            @Path(value = "person_id", encoded = true) actorId: String,
            @Query("api_key") apiKey: String): Observable<PersonCredits>

    @GET("3/person/popular")
    fun getPopularPeople(
            @Query("api_key") apiKey: String,
            @Query("page") pageNumber: String): Observable<PersonResults>

    @GET("3/search/person")
    fun getPersonSearchResults(
            @Query("api_key") apiKey: String,
            @Query("query") query: String,
            @Query("page") pageNumber: String): Observable<PersonResults>
}