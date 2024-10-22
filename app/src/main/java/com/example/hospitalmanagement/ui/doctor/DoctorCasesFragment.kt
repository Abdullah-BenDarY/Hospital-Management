package com.example.hospitalmanagement.ui.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.databinding.FragmentDoctorCasesBinding
import com.example.hospitalmanagement.databinding.FragmentHomeBinding
import com.example.hospitalmanagement.ui.home.HomeFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCasesFragment : BaseFragment<FragmentDoctorCasesBinding, BaseViewModel>() {
    private val doctorViewModel: BaseViewModel by viewModels()
    private val args by navArgs<HomeFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoctorCasesBinding.inflate(inflater, container, false)

    override fun initViewModel(): BaseViewModel {
        return doctorViewModel
    }
}