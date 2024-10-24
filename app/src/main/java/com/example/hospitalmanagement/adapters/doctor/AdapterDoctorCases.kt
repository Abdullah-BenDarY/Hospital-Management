package com.example.hospitalmanagement.adapters.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.CasesItem
import com.example.hospitalmanagement.databinding.ItemDoctorCasesBinding

class AdapterDoctorCases  : RecyclerView.Adapter<AdapterDoctorCases.Holder>() {
    private var casesData: List<CasesItem?>?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemDoctorCasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount() = casesData?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = casesData?.get(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    private var onClick: (Int) -> Unit = {}
    fun setOnClick(onAcceptClick: (Int) -> Unit) {
        this.onClick = onAcceptClick
    }

    inner class Holder(private val binding: ItemDoctorCasesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnShowDetails.setOnClickListener {
                casesData!![layoutPosition]!!.id?.let { id ->
                    onClick.invoke(id)
                }
            }
        }

        fun bind(data: CasesItem) {
            binding.apply {
                tvUsername.text = data.patientName
                tvDate.text = data.createdAt
            }
        }
    }

    fun submitList(data: List<CasesItem?>?= null) {
        casesData = data
        notifyDataSetChanged()
    }
}