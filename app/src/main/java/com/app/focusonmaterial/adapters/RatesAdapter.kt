package com.app.focusonmaterial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.focusonmaterial.databinding.RowItemRatesBinding
import com.app.focusonmaterial.models.Rates

class RatesAdapter(private var ratesList: List<Rates>) :
    RecyclerView.Adapter<RatesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowItemRatesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemRatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(ratesList[position]) {
                binding.apply {
                    tvRateName.text = rateName
                    tvRateValue.text = rateValue.toString()
                }
            }
        }
    }

    override fun getItemCount() = ratesList.size

    fun addRates(data: List<Rates>) {
        ratesList = data
        notifyDataSetChanged()
    }
}