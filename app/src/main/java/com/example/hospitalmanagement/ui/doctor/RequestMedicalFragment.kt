package com.example.hospitalmanagement.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentRequestMedicalBinding
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorContract
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorViewModel
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestMedicalFragment : BaseFragment<FragmentRequestMedicalBinding , DoctorContract.ViewModel>() {
    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private val args by navArgs<RequestMedicalFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentRequestMedicalBinding.inflate(inflater , container , false)
    override fun initViewModel(): DoctorContract.ViewModel  = doctorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMessage("CaseId ${args.caseId} , NurseId ${args.userId}")
    }
}