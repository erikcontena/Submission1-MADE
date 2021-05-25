package com.contena.submission1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.contena.core.data.source.remote.network.ApiResponse
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.usecase.MainUseCase
import com.contena.core.utils.Resource

class TvShowViewModel(private val useCase: MainUseCase): ViewModel() {
    fun getTvShows(query: String): LiveData<Resource<List<Catalog>>> = useCase.getTvShows(query).asLiveData()
    suspend fun getSearchTvShows(query: String): LiveData<ApiResponse<List<Catalog>>> = useCase.getSearchTvShows(query).asLiveData()
}
