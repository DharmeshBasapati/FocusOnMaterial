package com.app.focusonmaterial.api

import com.app.focusonmaterial.models.Rates
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://openexchangerates.org/api/historical/"


    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiServices: ApiServices = getRetrofit().create(ApiServices::class.java)

}