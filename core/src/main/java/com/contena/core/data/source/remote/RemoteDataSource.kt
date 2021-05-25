package com.contena.core.data.source.remote

import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.data.source.remote.network.ApiService
import com.contena.core.data.source.remote.response.MovieResponses
import com.contena.core.data.source.remote.response.TvShowResponses
import com.contena.core.domain.model.Catalog
import com.contena.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){


    suspend fun getMovies() : Flow<ApiResponse<List<MovieResponses>>>  = flow{
        try {
            val responses = apiService.getMovies()
            if (responses.results.isNotEmpty()) {
                emit(ApiResponse.Success(responses.results))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (exception: Exception){
            emit(ApiResponse.Error(exception.message.toString() ))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getTvShows(): Flow<ApiResponse<List<TvShowResponses>>> = flow{
            try {
                val responses = apiService.getTvShows()
                if (responses.results.isNotEmpty()) {
                    emit(ApiResponse.Success(responses.results))
                }else{
                    emit(ApiResponse.Empty)
                }

            }catch (exception: Exception){
                emit(ApiResponse.Error(exception.message.toString() ))
            }
        }.flowOn(Dispatchers.IO)


    suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<Catalog>>> =  flow{
        try {
            val responses = apiService.getSearchMovies(query)
            if (responses.results.isNotEmpty()) {
                emit(ApiResponse.Success(DataMapper.mapResponseMoviesToDomain(responses.results)))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (exception: Exception){
            emit(ApiResponse.Error(exception.message.toString() ))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSearchTvShows(query: String): Flow<ApiResponse<List<Catalog>>> = flow{
        try {
            val responses = apiService.getSearchTvShows(query)
            if (responses.results.isNotEmpty()) {
                emit(ApiResponse.Success(DataMapper.mapResponseTvShowsToDomain(responses.results)))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (exception: Exception){
            emit(ApiResponse.Error(exception.message.toString() ))
        }
    }.flowOn(Dispatchers.IO)
}