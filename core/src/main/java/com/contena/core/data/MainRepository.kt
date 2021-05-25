package com.contena.core.data

import android.util.Log
import com.contena.core.data.source.local.LocalDataSource
import com.contena.core.data.source.remote.RemoteDataSource
import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.data.source.remote.response.MovieResponses
import com.contena.core.data.source.remote.response.TvShowResponses
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.repository.IMainRepository
import com.contena.core.utils.AppExecutors
import com.contena.core.utils.DataMapper
import com.contena.core.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMainRepository {


    override fun getMovies(query: String): Flow<Resource<List<Catalog>>> =
        object : NetworkBoundResource<List<Catalog>, List<MovieResponses>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Catalog>> =
                localDataSource.getMovies(query).map {
                    DataMapper.mapEntityMoviesToDomain(it)
                }

            override fun shouldFetch(data: List<Catalog>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponses>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponses>) {

                localDataSource.insertMovies(DataMapper.mapMovies(data))
            }

        }.asFlow()

    override fun getTvShows(query: String): Flow<Resource<List<Catalog>>> =
        object : NetworkBoundResource<List<Catalog>, List<TvShowResponses>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Catalog>> =
                localDataSource.getTvShows(query).map {
                    DataMapper.mapEntityTvShowsToDomain(it)
                }

            override fun shouldFetch(data: List<Catalog>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponses>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponses>) {
                localDataSource.insertTvShows(DataMapper.mapTvShows(data))
            }

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Catalog>> =
        localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntityMoviesToDomain(it)
        }


    override fun getFavoriteTvShows(): Flow<List<Catalog>> =
        localDataSource.getFavoriteTvShows().map {
            DataMapper.mapEntityTvShowsToDomain(it)
        }

    override fun setFavoriteMovie(movie: Catalog, state: Boolean) {
        movie.isFavorite = state
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "setFavoriteMovie: INSERT")
            localDataSource.insertMovie(DataMapper.mapDomainMovieToEntity(movie))
        }

        appExecutors.diskIO().execute {
            Log.d("TAG", "setFavoriteMovie: UPDATE")
            localDataSource.setFavoriteMovie(
                DataMapper.mapDomainMovieToEntity(movie)
            )
        }
    }


    override fun setFavoriteTvShow(tvShow: Catalog, state: Boolean) {
        tvShow.isFavorite = state
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "setFavoriteTvShow: INSERT")
            localDataSource.insertTvShow((DataMapper.mapDomainTvShowToEntity(tvShow)))
        }
        appExecutors.diskIO().execute {
            Log.d("TAG", "setFavoriteTvShow: UPDATE")
            localDataSource.setFavoriteTvShow(
                DataMapper.mapDomainTvShowToEntity(tvShow)
            )
        }
    }

    override suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<Catalog>>> =
        remoteDataSource.getSearchMovies(query)

    override suspend fun getSearchTvShows(query: String): Flow<ApiResponse<List<Catalog>>> =
        remoteDataSource.getSearchTvShows(query)
}