package com.contena.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id:Int,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "release")
        var release: String,

        @ColumnInfo(name = "rating")
        var rating: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "imagepath")
        var imagePath: String,

        @ColumnInfo(name = "bannerpath")
        var bannerPath: String,

        @ColumnInfo(name = "isfavorite")
        var isFavorite : Boolean = false

)