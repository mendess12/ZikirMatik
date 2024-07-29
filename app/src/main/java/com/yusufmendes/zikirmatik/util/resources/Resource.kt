package com.yusufmendes.zikirmatik.util.resources

sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val errorMessage: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}