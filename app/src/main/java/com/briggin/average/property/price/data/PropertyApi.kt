package com.briggin.average.property.price.data

import retrofit2.http.GET

const val BASE_URL = " https://raw.githubusercontent.com/rightmove/Code-Challenge-Android/master/"

interface PropertyApi {

    @GET("properties.json")
    suspend fun getProperties(): PropertiesDto
}
