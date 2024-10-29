package com.example.hospitalmanagement.adapters.doctor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.CasesItem
import com.example.domain.models.UsersData
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.databinding.ItemCaseDetailsTabsBinding
import com.example.hospitalmanagement.databinding.ItemNursesListBinding
import com.example.hospitalmanagement.utils.CASE
import com.example.hospitalmanagement.utils.MEDICAL_MEASUREMENT
import com.example.hospitalmanagement.utils.MEDICAL_RECORD

class AdapterNursesList : RecyclerView.Adapter<AdapterNursesList.Holder>() {

    private var nursesList: List<UsersData?>?= null
    private var selectedPossision = -1

    private lateinit var onClick: (Int ) -> Unit?
    fun setOnClick(onClick: (Int ) -> Unit) {
        this.onClick = onClick}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemNursesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding) }

    override fun getItemCount(): Int = nursesList?.size ?: 0

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val nurseList = nursesList!![position]

        holder.bind(nurseList,position == selectedPossision)
        holder.binding.rdSelect.setOnClickListener {
            checkSelected(position , nurseList)
        }
        holder.binding.root.setOnClickListener {
            checkSelected(position , nurseList)
        }
    }

    inner class Holder(val binding: ItemNursesListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(list: UsersData? ,isSelected: Boolean) {
            binding.apply {
                rdSelect.isChecked = isSelected
                tvUserName.text = list?.first_name
                tvUserDetails.text = list?.type
            }
        }
    }

    fun submitList(data:List<UsersData?>?= null) {
        nursesList = data
        notifyDataSetChanged()
    }

    private fun checkSelected(position: Int , nurseList: UsersData?) {
        onClick.invoke(nurseList?.id?: -1)
        if (position != selectedPossision) {
            val previousPossition = selectedPossision
            selectedPossision = position
            notifyItemChanged(previousPossition)
            notifyItemChanged(position)
        }
    }
}