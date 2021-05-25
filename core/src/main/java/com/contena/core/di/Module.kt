package com.contena.core.di

import androidx.room.Room
import com.contena.core.BuildConfig
import com.contena.core.data.MainRepository
import com.contena.core.data.source.local.LocalDataSource
import com.contena.core.data.source.local.room.CatalogDatabase
import com.contena.core.data.source.remote.RemoteDataSource
import com.contena.core.data.source.remote.network.ApiService
import com.contena.core.domain.repository.IMainRepository
import com.contena.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<CatalogDatabase>().catalogDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogDatabase::class.java,
            "catalog.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMainRepository> { MainRepository(get(), get(), get()) }
}