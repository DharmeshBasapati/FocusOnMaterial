package com.app.focusonmaterial.api

import com.app.focusonmaterial.models.HistoricalRatesResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @POST("{date}.json?app_id=b8170f2960a546378a5ceb06a7bb6f59")
    fun getHistoricalRates(@Path(value = "date") date: String): Call<HistoricalRatesResponse>

}