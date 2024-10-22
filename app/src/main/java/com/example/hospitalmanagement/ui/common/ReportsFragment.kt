package com.example.hospitalmanagement.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.databinding.FragmentReportsBinding
import com.example.hospitalmanagement.ui.home.HomeFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportsFragment : BaseFragment<FragmentReportsBinding, BaseViewModel>() {
    private val commonViewModel: BaseViewModel by viewModels()
    private val args by navArgs<HomeFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReportsBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return commonViewModel
    }
}