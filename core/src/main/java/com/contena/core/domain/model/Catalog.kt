package com.contena.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Catalog(
        var id:Int,
        var title: String,
        var release: String,
        var rating: String,
        var description: String,
        var imagePath: String,
        var bannerPath: String,
        var isFavorite : Boolean
): Parcelable