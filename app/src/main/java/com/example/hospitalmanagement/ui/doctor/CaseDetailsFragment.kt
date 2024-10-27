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
import androidx.navigation.fragment.navArgs
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.adapters.doctor.AdapterCaseDetailsTabs
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentCaseDetailsBinding
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorContract
import com.example.hospitalmanagement.ui.doctor.viewModel.DoctorViewModel
import com.example.hospitalmanagement.utils.CASE
import com.example.hospitalmanagement.utils.MEDICAL_MEASUREMENT
import com.example.hospitalmanagement.utils.MEDICAL_RECORD
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CaseDetailsFragment : BaseFragment<FragmentCaseDetailsBinding, DoctorContract.ViewModel>() {
    private val adapterDoctorCalls = AdapterCaseDetailsTabs()
    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private val args: CaseDetailsFragmentArgs by navArgs()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCaseDetailsBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel {
        return doctorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTabs.adapter = adapterDoctorCalls
        setOnTabsClick(CASE)
        initClickListeners()
        observeEndCase()
    }

    private fun initClickListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnEndCase.setOnLongClickListener {
                doctorViewModel.doIntent(DoctorContract.Intent.EndCase(args.caseId))
                true
            }
        }
        adapterDoctorCalls.setOnClick {
            setOnTabsClick(it)
        }
    }

    private fun observeEndCase() {
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
            is DoctorContract.Event.CaseEnded -> onEndCaseaClickActions()
        }
    }

    private fun handleStates(state: DoctorContract.State?) {
        when (state) {
            is DoctorContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is DoctorContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }


    //observe
    private fun onEndCaseaClickActions() {
        binding.apply {
            btnEndCase.isEnabled = false
            btnEndCase.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            btnEndCase.text = getString(R.string.case_ended)
            btnEndCase.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_header))
        }
    }

    private fun setOnTabsClick(item: String) {
        val fragment = when (item) {
            CASE -> DoctorSubDetailsFragment()
            MEDICAL_RECORD -> CaseMedicalRecordFragment()
            MEDICAL_MEASUREMENT -> CaseMedicalMeasurementFragment()
            else -> null
        }
        fragment?.let {
            it.arguments = Bundle().apply {
                putInt("caseId", args.caseId)}
            childFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.frCaseDetails, it)
                    commit()
                }
        }
    }
}