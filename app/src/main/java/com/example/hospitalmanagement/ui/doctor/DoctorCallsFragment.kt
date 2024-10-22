package com.example.hospitalmanagement.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.databinding.FragmentDoctorCallsBinding
import com.example.hospitalmanagement.ui.home.HomeFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCallsFragment : BaseFragment<FragmentDoctorCallsBinding , BaseViewModel>() {


    private val doctorViewModel: BaseViewModel by viewModels()
    private val args by navArgs<HomeFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoctorCallsBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return doctorViewModel
    }
}