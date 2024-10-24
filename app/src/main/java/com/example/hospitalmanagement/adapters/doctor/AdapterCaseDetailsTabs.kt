package com.example.hospitalmanagement.adapters.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.databinding.ItemCaseDetailsTabsBinding
import com.example.hospitalmanagement.utils.CASE
import com.example.hospitalmanagement.utils.MEDICAL_MEASUREMENT
import com.example.hospitalmanagement.utils.MEDICAL_RECORD

class AdapterCaseDetailsTabs : RecyclerView.Adapter<AdapterCaseDetailsTabs.Holder>() {

    private val tabsList = listOf(CASE, MEDICAL_RECORD, MEDICAL_MEASUREMENT)
    private var selectedPossision = 0

    private lateinit var onClick: (String ) -> Unit?
    fun setOnClick(onClick: (String ) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCaseDetailsTabsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding) }

    override fun getItemCount(): Int = tabsList.size ?: 0

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val tabsList = tabsList[position]
        holder.bind(tabsList, position == selectedPossision)
        holder.binding.btnTabs.setOnClickListener {
            onClick.invoke(tabsList)

            if (position != selectedPossision) {
                val previousPossition = selectedPossision
                selectedPossision = position
                notifyItemChanged(previousPossition)
                notifyItemChanged(position)
            }
        }
    }
    inner class Holder(val binding: ItemCaseDetailsTabsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val mintGreen = ContextCompat.getColorStateList(binding.root.context, R.color.mintGreen)
        private val white = ContextCompat.getColorStateList(binding.root.context, R.color.white)
        private val black = ContextCompat.getColorStateList(binding.root.context, R.color.black)

        fun bind(tab : String, isSelected: Boolean) {
            if (isSelected) {
                binding.btnTabs.setTextColor(white)
                binding.btnTabs.backgroundTintList = mintGreen
            } else {
                binding.btnTabs.setTextColor(black)
                binding.btnTabs.backgroundTintList = white
            }
            binding.btnTabs.text = tab
        }
    }
}