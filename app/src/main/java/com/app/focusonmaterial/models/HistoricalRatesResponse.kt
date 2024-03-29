package com.app.focusonmaterial.models

import com.google.gson.annotations.SerializedName

data class HistoricalRatesResponse(
    @SerializedName("disclaimer") val disclaimer : String,
    @SerializedName("license") val license : String,
    @SerializedName("timestamp") val timestamp : Int,
    @SerializedName("base") val base : String,
    @SerializedName("rates") val rates : Map<String,Double>
)