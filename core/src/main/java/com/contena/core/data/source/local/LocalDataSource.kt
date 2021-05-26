package com.contena.core.data.source.local

import com.contena.core.data.source.local.entity.MovieEntity
import com.contena.core.data.source.local.entity.TvShowEntity
import com.contena.core.data.source.local.room.CatalogDao
import com.contena.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val catalogDao: CatalogDao){

    fun getMovies(query: String) : Flow<List<MovieEntity>> =
        catalogDao.getMovies(SortUtils.getSortedMovies(query))

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = catalogDao.getFavoriteMovies()

    fun getTvShows(query: String): Flow<List<TvShowEntity>> = catalogDao.getTvShows(SortUtils.getSortedTvShows(query))

    fun getFavoriteTvShows() : Flow<List<TvShowEntity>> = catalogDao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = catalogDao.insertTvShows(tvShows)
    suspend fun insertMovies(movies: List<MovieEntity>) = catalogDao.insertMovies(movies)

    suspend fun insertMovie(movie: MovieEntity) = catalogDao.insertMovie(movie)
    suspend fun  insertTvShow(tvShow: TvShowEntity) = catalogDao.inserrTvShow(tvShow)

    suspend fun setFavoriteMovie(movie : MovieEntity) {
        catalogDao.updateFavoriteMovie(movie)
    }
    suspend fun setFavoriteTvShow(tvShow : TvShowEntity) {
        catalogDao.updateFavoriteTvShow(tvShow)
    }



}