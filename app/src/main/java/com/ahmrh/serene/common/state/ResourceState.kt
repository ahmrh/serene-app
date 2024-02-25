package com.ahmrh.serene.common.state

import java.lang.Exception

sealed class ResourceState<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
//    class Loading<T>(data: T? = null) : ResourceState<T>(data)
    class Success<T>(data: T) : ResourceState<T>(data)
    class Failed<T>(exception: Exception, data: T? = null) :
        ResourceState<T>(data, exception)

}
