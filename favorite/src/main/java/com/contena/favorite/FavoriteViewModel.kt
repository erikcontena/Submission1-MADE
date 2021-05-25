package com.contena.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.usecase.MainUseCase

class FavoriteViewModel(private val useCase: MainUseCase): ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Catalog>> = useCase.getFavoriteMovies().asLiveData()

    fun getFavoriteTvShows(): LiveData<List<Catalog>> = useCase.getFavoriteTvShows().asLiveData()

}