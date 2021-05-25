package com.contena.submission1.ui.detail

import androidx.lifecycle.ViewModel
import com.contena.core.domain.model.Catalog
import com.contena.core.domain.usecase.MainUseCase

class DetailViewModel(private val useCase: MainUseCase) : ViewModel() {


    fun setMovieFavorite(catalog: Catalog) {
        val newState = !catalog.isFavorite
        useCase.setFavoriteMovie(catalog, newState)
    }

    fun setTvShowFavorit(catalog: Catalog) {
        val newState = !catalog.isFavorite
        useCase.setFavoriteTvShow(catalog, newState)
    }
}
