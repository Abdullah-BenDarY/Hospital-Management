package com.example.hospitalmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentCaseMedicalRecordBinding
import com.example.hospitalmanagement.ui.doctor.DoctorContract
import com.example.hospitalmanagement.ui.doctor.DoctorViewModel
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseMedicalRecordFragment : BaseFragment<FragmentCaseMedicalRecordBinding, DoctorContract.ViewModel>() {

    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private var caseId: Int? = null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCaseMedicalRecordBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel {
        return doctorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caseId = arguments?.getInt("caseId")
        showMessage(caseId)
    }
}