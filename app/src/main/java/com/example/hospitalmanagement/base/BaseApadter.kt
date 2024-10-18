package com.example.hospitalmanagement.base


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.hospitalmanagement.R
import com.example.news.R

abstract class  BaseAdapter<TypeItemList, VB : ViewBinding>(private val alertDialog: AlertDialog) :
    RecyclerView.Adapter<BaseAdapter<TypeItemList, VB>.ViewHolder>() {
    private var items: MutableList<TypeItemList>? = null

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    abstract fun getBinding(parent: ViewGroup, viewType: Int): VB

    abstract fun bindData(binding: VB, item: TypeItemList, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        bindData(holder.binding, item!!, position)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    fun addDataToList(item: TypeItemList) {
        items?.add(item)
        notifyItemChanged(items?.size!! - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun addDataToList(items: List<TypeItemList>) {
        if (this.items == null) {
            this.items = mutableListOf()
        }
        this.items?.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(items: List<TypeItemList>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        items?.removeAt(position)
        notifyItemChanged(position)
    }

    fun removeItem(item: TypeItemList) {
        val index = items?.indexOf(item) ?: -1
        if (index != -1)
            items!!.removeAt(index)
        return
    }

    @SuppressLint("InflateParams")
    open fun showProgressDialog() {
        alertDialog.apply {
            setCancelable(true)
            setView(
                LayoutInflater.from(alertDialog.context)
                    .inflate(R.layout.loading_dialog_layout, null)
            )
            window?.setBackgroundDrawableResource(R.color.transparent)
            show()
        }
    }

    fun dismissProgressDialog() {
        alertDialog.dismiss()
    }
}