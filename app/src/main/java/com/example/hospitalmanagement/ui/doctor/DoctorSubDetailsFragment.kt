package com.example.hospitalmanagement.ui.doctor

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.models.CaseData
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentDoctorSubDetailsBinding
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorContract
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorViewModel
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoctorSubDetailsFragment : BaseFragment<FragmentDoctorSubDetailsBinding, DoctorContract.ViewModel>() {

    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private var caseId: Int? = null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoctorSubDetailsBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel {
        return doctorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caseId = arguments?.getInt("caseId")
        viewModel.doIntent(DoctorContract.Intent.GetCaseDetails(caseId!!))
        observeDoctorCalls()
        initClicks()
    }

    private fun observeDoctorCalls() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: DoctorContract.Event) {
        when (event) {
            DoctorContract.Event.InitialEvent -> {}
            is DoctorContract.Event.ShowCaseData-> setUpViewUi(event.modelCaseDetails.data!!)
        }
    }

    private fun handleStates(state: DoctorContract.State?) {
        when (state) {
            is DoctorContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is DoctorContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun initClicks() {
        binding.apply {
            btnRequest.setOnClickListener {
                showMessage("Request Sent")
            }
            btnAddNurce.setOnClickListener {
                findNavController().navigate(CaseDetailsFragmentDirections.globalActionToAddNurseFragment(caseId!!))
            }
            btnRequest.setOnClickListener {
                findNavController().navigate(CaseDetailsFragmentDirections.actionToDoctorRequestDialog(caseId!!))
            }
        }
    }

    private fun setUpViewUi(caseData: CaseData) {
        binding.apply {
            tvPatientName.text = caseData.patientName
            tvPatientAge.text = caseData.age
            tvPatientNumber.text = caseData.phone
            tvPatientDate.text = caseData.createdAt
            tvPatientDoctor.text = caseData.doctorId
            tvPatientNurse.text = caseData.nurseId ?: "Not assigned"
            tvPatientStatus.text = caseData.caseStatus
            tvPatientDescription.text = caseData.description
            caseData.nurseId?.isBlank().let {
                if (it == false) {
                    onNurseAdedActions()
                } else return
            }
        }
    }

    private fun onNurseAdedActions() {
        binding.apply {
            btnAddNurce.apply {
                isEnabled = false
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.text_header))
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
                text = getString(R.string.nurse_aded)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.text_header))
            }
        }
    }
}