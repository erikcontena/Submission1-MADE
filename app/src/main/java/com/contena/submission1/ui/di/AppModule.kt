package com.contena.submission1.ui.di

import com.contena.core.domain.usecase.MainInteractor
import com.contena.core.domain.usecase.MainUseCase
import com.contena.submission1.ui.detail.DetailViewModel
import com.contena.submission1.ui.movie.MovieViewModel
import com.contena.submission1.ui.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <MainUseCase>{ MainInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}