package com.app.focusonmaterial

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.focusonmaterial.adapters.RatesAdapter
import com.app.focusonmaterial.api.RetrofitBuilder
import com.app.focusonmaterial.databinding.ActivityRatesBinding
import com.app.focusonmaterial.models.HistoricalRatesResponse
import com.app.focusonmaterial.models.Rates
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RatesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatesBinding
    private lateinit var ratesAdapter: RatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRates.layoutManager = GridLayoutManager(this, 3)
        ratesAdapter = RatesAdapter(arrayListOf())
        binding.rvRates.adapter = ratesAdapter

        val simpleFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val currentDate = Date()
        getExchangeRates(simpleFormat.format(currentDate))

        binding.btnChooseDate.setOnClickListener {

            val constraintsBuilder = CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())

            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Choose date")
                    .setCalendarConstraints(constraintsBuilder.build())
                    .build()

            datePicker.show(supportFragmentManager, "tag");

            datePicker.addOnPositiveButtonClickListener {

                val timeZoneUTC: TimeZone = TimeZone.getDefault()

                val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().time) * -1

                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

                val selectedDate = Date(it + offsetFromUTC)

                getExchangeRates(simpleDateFormat.format(selectedDate))
            }
        }


    }

    private fun getExchangeRates(date: String) {

        binding.rvRates.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE

        RetrofitBuilder.apiServices.getHistoricalRates(date)
            .enqueue(object : Callback<HistoricalRatesResponse> {
                override fun onResponse(
                    call: Call<HistoricalRatesResponse>,
                    response: Response<HistoricalRatesResponse>
                ) {

                    val ratesList = ArrayList<Rates>()
                    val rateKeys = response.body()?.rates?.keys
                    val rateValues = response.body()?.rates?.values

                    for (i in 0 until response.body()?.rates?.size!!) {
                        ratesList.add(Rates(rateKeys?.elementAt(i)!!, rateValues?.elementAt(i)!!))
                    }

                    ratesAdapter.addRates(ratesList)

                    binding.tvSelectedDate.text = "Showing rates for $date"
                    binding.rvRates.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE

                }

                override fun onFailure(call: Call<HistoricalRatesResponse>, t: Throwable) {
                    Log.d("TAG", "onResponse: ${t.message}")
                    binding.rvRates.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

            })


    }
}