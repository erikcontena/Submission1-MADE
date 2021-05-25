package com.contena.core.domain.usecase

import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.repository.IMainRepository
import com.contena.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class MainInteractor(private val repository: IMainRepository) : MainUseCase {
    override fun getMovies(query: String): Flow<Resource<List<Catalog>>> =
        repository.getMovies(query)

    override fun getTvShows(query: String): Flow<Resource<List<Catalog>>> =
        repository.getTvShows(query)

    override fun getFavoriteMovies(): Flow<List<Catalog>> = repository.getFavoriteMovies()

    override fun getFavoriteTvShows(): Flow<List<Catalog>> = repository.getFavoriteTvShows()

    override fun setFavoriteMovie(movie: Catalog, state: Boolean) {
        repository.setFavoriteMovie(movie, state)
    }

    override fun setFavoriteTvShow(tvShow: Catalog, state: Boolean) {
        repository.setFavoriteTvShow(tvShow, state)
    }

    override suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<Catalog>>> =
        repository.getSearchMovies(query)

    override suspend fun getSearchTvShows(query: String): Flow<ApiResponse<List<Catalog>>> =
        repository.getSearchTvShows(query)

}