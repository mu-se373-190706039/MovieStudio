package xyz.scoca.moviestudio.network

sealed class StatusModel<out T : Any>

object OnLoading : StatusModel<Nothing>()
data class OnSuccess<out T : Any>(val data : T) : StatusModel<T>()
data class OnError<out T : Any>(val msg : String) : StatusModel<Nothing>()
