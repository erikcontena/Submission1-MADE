package com.contena.core.utils

import com.contena.core.data.source.local.entity.MovieEntity
import com.contena.core.data.source.local.entity.TvShowEntity
import com.contena.core.data.source.remote.response.MovieResponses
import com.contena.core.data.source.remote.response.TvShowResponses
import com.contena.core.domain.model.Catalog


object DataMapper {

    fun mapMovies(input: List<MovieResponses>): List<MovieEntity> {
        val catalogueEntity = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.originalTitle,
                release = it.releaseDate,
                rating = it.voteAverage.toString(),
                description = it.overview,
                imagePath = it.posterPath ?: "",
                bannerPath = it.backdropPath?: "",
            )
            catalogueEntity.add(movie)
        }
        return catalogueEntity
    }

    fun mapTvShows(input: List<TvShowResponses>): List<TvShowEntity> {
        val entity = ArrayList<TvShowEntity>()
        input.map {
            val tvshow = TvShowEntity(
                id = it.id,
                title = it.originalTitle,
                release = it.releaseDate,
                rating = it.voteAverage.toString(),
                description = it.overview,
                imagePath = it.posterPath?:"",
                bannerPath = it.backdropPath?:"",
            )
            entity.add(tvshow)
        }
        return entity
    }
    fun mapEntityMoviesToDomain(input: List<MovieEntity>): List<Catalog> {
        val entity = ArrayList<Catalog>()
        input.map {
            val catalog = Catalog(
                id = it.id,
                title = it.title,
                release = it.release,
                rating = it.rating,
                description = it.description,
                imagePath = it.imagePath,
                bannerPath = it.bannerPath,
                isFavorite = it.isFavorite
            )
            entity.add(catalog)
        }
        return entity
    }

    fun mapEntityTvShowsToDomain(input: List<TvShowEntity>): List<Catalog> {
        val entity = ArrayList<Catalog>()
        input.map {
            val catalog = Catalog(
                id = it.id,
                title = it.title,
                release = it.release,
                rating = it.rating,
                description = it.description,
                imagePath = it.imagePath,
                bannerPath = it.bannerPath,
                isFavorite = it.isFavorite
            )
            entity.add(catalog)
        }
        return entity
    }


    fun mapResponseMoviesToDomain(input: List<MovieResponses>): List<Catalog> {
        val entity = ArrayList<Catalog>()
        input.map {
            val catalog = Catalog(
                id = it.id,
                title = it.originalTitle,
                release = it.releaseDate,
                rating = it.voteAverage.toString(),
                description = it.overview,
                imagePath = it.posterPath?:"",
                bannerPath = it.backdropPath?:"",
                isFavorite = false
            )
            entity.add(catalog)
        }
        return entity
    }

    fun mapResponseTvShowsToDomain(input: List<TvShowResponses>): List<Catalog> {
        val entity = ArrayList<Catalog>()
        input.map {
            val catalog = Catalog(
                id = it.id,
                title = it.originalTitle,
                release = it.releaseDate,
                rating = it.voteAverage.toString(),
                description = it.overview,
                imagePath = it.posterPath?:"",
                bannerPath = it.backdropPath?:"",
                isFavorite = false
            )
            entity.add(catalog)
        }
        return entity
    }

    fun mapDomainMovieToEntity(it: Catalog): MovieEntity = MovieEntity(
        id = it.id,
        title = it.title,
        release = it.release,
        rating = it.rating,
        description = it.description,
        imagePath = it.imagePath,
        bannerPath = it.bannerPath,
        isFavorite = it.isFavorite
    )

    fun mapDomainTvShowToEntity(it: Catalog): TvShowEntity = TvShowEntity(
        id = it.id,
        title = it.title,
        release = it.release,
        rating = it.rating,
        description = it.description,
        imagePath = it.imagePath,
        bannerPath = it.bannerPath,
        isFavorite = it.isFavorite
    )


}