package com.yyusufsefa.videogameappcompose.core.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

fun <T> apiFlow(
    call: suspend () -> T
): Flow<Resource<T>> = flow {
    emit(Resource.Loading)
    try {
        val response = call()
        emit(Resource.Success(response))
    } catch (e: HttpException) {
        emit(Resource.Error(e.message ?: "An unexpected error occurred"))
    } catch (e: Exception) {
        emit(Resource.Error(e.message ?: "An unexpected error occurred"))
    }
}