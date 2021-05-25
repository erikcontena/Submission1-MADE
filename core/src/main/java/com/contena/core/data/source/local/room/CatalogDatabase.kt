package com.contena.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.contena.core.data.source.local.entity.MovieEntity
import com.contena.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao
}