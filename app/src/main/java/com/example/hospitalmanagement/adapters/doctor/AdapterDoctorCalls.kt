package com.example.hospitalmanagement.adapters.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.DoctorCallsData
import com.example.hospitalmanagement.databinding.ItemDoctorCallsBinding

class AdapterDoctorCalls : RecyclerView.Adapter<AdapterDoctorCalls.CallsHolder>() {
    private var callsData: MutableList<DoctorCallsData?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsHolder {
        val binding =
            ItemDoctorCallsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsHolder(binding)
    }

    override fun getItemCount() = callsData?.size ?: 0

    override fun onBindViewHolder(holder: CallsHolder, position: Int) {
        val calls = callsData?.get(position)
        if (calls != null) {
            holder.bind(calls)
        }
//        deleteItem(position)
    }

    private var onAcceptClick: (Int) -> Unit = {}
    fun setOnAcceptClick(onAcceptClick: (Int) -> Unit) {
        this.onAcceptClick = onAcceptClick
    }

    private var onBusyClick: (Int) -> Unit = {}
    fun setOnBussyClick(onBusyClick: (Int) -> Unit) {
        this.onBusyClick = onBusyClick
    }

    inner class CallsHolder(private val binding: ItemDoctorCallsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Accept button click
            binding.btnAccept.setOnClickListener {
                callsData!![layoutPosition]?.id?.let { id ->
                    onAcceptClick.invoke(id)
                }
            }

            // Busy button click
            binding.btnBusy.setOnClickListener {
                callsData!![layoutPosition]?.id?.let { id ->
                    onBusyClick.invoke(id)
                }
            }

        }

        fun bind(calls: DoctorCallsData) {
            binding.apply {
                tvUsername.text = calls.patientName
                tvDate.text = calls.createdAt
            }
        }
    }

    fun submitList(calls: MutableList<DoctorCallsData?>?) {
        callsData = calls
        notifyDataSetChanged()
    }
    private fun deleteItemById(id: Int) {
        val position = callsData!!.indexOfFirst { it?.id == id }
        if (position != -1 && position in callsData!!.indices) {
            callsData?.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun checkStatusAndRemoveItem(status: Int, id: Int) {
        if (status == 1) {
            deleteItemById(id)
        }
    }

}
