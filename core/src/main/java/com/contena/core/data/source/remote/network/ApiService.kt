package com.contena.core.data.source.remote.network

import com.contena.core.BuildConfig
import com.contena.core.data.source.remote.response.MovieResponses
import com.contena.core.data.source.remote.response.Response
import com.contena.core.data.source.remote.response.TvShowResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Response<MovieResponses>


    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Response<TvShowResponses>

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Response<MovieResponses>

    @GET("search/tv")
    suspend fun getSearchTvShows(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Response<TvShowResponses>


}
