package com.xavidev.testessential.data

sealed class Response<out T>(
    val data: T? = null,
    val status: State? = null,
    val message: String? = null
) {
    class Loading<T>(status: State = State.LOADING) : Response<T>(null, status)

    class Success<T>(data: T? = null, status: State = State.SUCCESS) : Response<T>(data, status)

    class Error<T>(message: String?, status: State = State.ERROR) :
        Response<T>(null, status, message)
}