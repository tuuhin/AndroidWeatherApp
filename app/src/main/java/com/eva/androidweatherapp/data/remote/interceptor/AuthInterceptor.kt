package com.eva.androidweatherapp.data.remote.interceptor

import com.eva.androidweatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {

    private const val AUTH_HEADER = "key"
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val updatedUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(name = AUTH_HEADER, value = BuildConfig.API_KEY)
            .build()

        val updatedRequest = originalRequest
            .newBuilder()
            .url(updatedUrl)
            .build()

        return chain.proceed(updatedRequest)
    }
}