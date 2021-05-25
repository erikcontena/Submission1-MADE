package com.contena.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val ORIGINAL = "good"
    const val RANDOM = "Random"

    fun getSortedMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentity")

        when (filter) {
            ORIGINAL -> {
                simpleQuery.append("")
            }
            RANDOM -> {
                simpleQuery.append(" ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
    fun getSortedTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshowentity")

        when (filter) {
            ORIGINAL -> {
                simpleQuery.append("")
            }
            RANDOM -> {
                simpleQuery.append(" ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}