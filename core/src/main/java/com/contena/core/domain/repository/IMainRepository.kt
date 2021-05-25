package com.contena.core.domain.repository

import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.domain.model.Catalog
import com.contena.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getMovies(query: String): Flow<Resource<List<Catalog>>>
    fun getTvShows(query: String): Flow<Resource<List<Catalog>>>

    fun getFavoriteMovies(): Flow<List<Catalog>>
    fun getFavoriteTvShows(): Flow<List<Catalog>>

    fun setFavoriteMovie(movie: Catalog, state: Boolean)
    fun setFavoriteTvShow(tvShow: Catalog, state: Boolean)
    suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<Catalog>>>
    suspend fun getSearchTvShows(query: String): Flow<ApiResponse<List<Catalog>>>

}