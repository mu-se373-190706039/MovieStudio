package xyz.scoca.moviestudio.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import xyz.scoca.moviestudio.model.popular.PopularMovieResult
import xyz.scoca.moviestudio.model.toprated.TopRatedResult

@Parcelize
data class Movie(
    val topRatedMovie : TopRatedResult? = null,
    val popularMovie : PopularMovieResult? = null
) : Parcelable