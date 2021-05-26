package com.contena.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.contena.core.data.source.local.entity.MovieEntity
import com.contena.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity where isfavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateFavoriteMovie(movie: MovieEntity)

    //TVSHOW

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserrTvShow(tvshow: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvshows: List<TvShowEntity>)

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SupportSQLiteQuery): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentity where isfavorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>
    
    @Update
    suspend fun updateFavoriteTvShow(tvShow: TvShowEntity)
}