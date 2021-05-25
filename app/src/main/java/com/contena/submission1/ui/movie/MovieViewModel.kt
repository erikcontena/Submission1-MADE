package com.contena.submission1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.usecase.MainUseCase
import com.contena.core.utils.Resource

class MovieViewModel(private val useCase: MainUseCase) : ViewModel() {
    fun getMovies(query: String): LiveData<Resource<List<Catalog>>> =
        useCase.getMovies(query).asLiveData()

    suspend fun searchResult(text: String): LiveData<ApiResponse<List<Catalog>>> =
        useCase.getSearchMovies(text).asLiveData()

}

