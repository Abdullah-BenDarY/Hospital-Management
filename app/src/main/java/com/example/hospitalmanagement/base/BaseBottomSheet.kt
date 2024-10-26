package com.example.hospitalmanagement.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.utils.showMessage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet <VB : ViewBinding, VM : BaseViewModel> : BottomSheetDialogFragment() {
    private var _binding: VB? = null
    open val binding get() = _binding!!
    lateinit var viewModel: VM


    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun initViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}