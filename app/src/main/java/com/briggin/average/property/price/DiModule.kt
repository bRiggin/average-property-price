package com.briggin.average.property.price

import com.briggin.average.property.price.data.BASE_URL
import com.briggin.average.property.price.data.PropertyApi
import com.briggin.average.property.price.data.PropertyRepository
import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.presentation.PropertyViewModel
import com.google.gson.GsonBuilder
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory

val koinModule = module {

    viewModel { PropertyViewModel(get()) }

    factory { PropertyRepository(get()) }.bind(PropertyDataSource::class)

    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PropertyApi::class.java)
    }
}