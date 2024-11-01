package com.example.hospitalmanagement.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.base.BaseBottomSheet
import com.example.hospitalmanagement.databinding.BottomSheetRequestBinding
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorContract
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorViewModel
import com.example.hospitalmanagement.utils.showMessage
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestBottomSheet : BaseBottomSheet<BottomSheetRequestBinding, DoctorContract.ViewModel>() {
    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private val args by navArgs<RequestBottomSheetArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetRequestBinding = BottomSheetRequestBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel = doctorViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            btnMedicalRecord.setOnClickListener { toggleButtonSelection(btnMedicalRecord, btnMedicalMeasurement) }
            btnMedicalMeasurement.setOnClickListener { toggleButtonSelection(btnMedicalMeasurement,btnMedicalRecord) }
            btnRequest.setOnClickListener {
                when {
                    btnMedicalRecord.isSelected -> showMessage("Medical Record ${args.caseId}")
                    btnMedicalMeasurement.isSelected ->
                        if (args.nurseId <= 0) {
                            showMessage("Please select nurse first")
                        } else
                            findNavController().navigate(
                                RequestBottomSheetDirections.actionCaseDetailsFragmentToRequestMedicalFragment(
                                    args.caseId,
                                    args.nurseId
                                )
                            )
                    else -> showMessage("Null")
                }
            }
        }
    }

    private fun toggleButtonSelection(selected: MaterialButton, unselected: MaterialButton) {
        selected.isSelected = true
        unselected.isSelected = false
    }
}