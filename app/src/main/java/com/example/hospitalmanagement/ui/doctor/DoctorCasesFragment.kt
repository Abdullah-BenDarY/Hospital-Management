package com.example.hospitalmanagement.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.models.CasesItem
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.adapters.doctor.AdapterDoctorCases
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentDoctorCasesBinding
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoctorCasesFragment : BaseFragment<FragmentDoctorCasesBinding, DoctorContract.ViewModel>() {
    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()
    private val adapterDoctorCases = AdapterDoctorCases()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoctorCasesBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel {
        return doctorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doIntent(DoctorContract.Intent.GetDoctorCases)
        observeDoctorCalls()
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            btnBack.setOnClickListener{
                findNavController().navigateUp()
            }
        }
        adapterDoctorCases.setOnClick {
            findNavController().navigate(
                DoctorCasesFragmentDirections
                    .actionDoctorCasesFragmentToCaseDetailsFragment(it))
        }
    }

    private fun observeDoctorCalls(id :Int ?= null) {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it, id)
            }
        }
    }

    private fun handleEvents(event: DoctorContract.Event , id :Int? = null) {
        when (event) {
            DoctorContract.Event.InitialEvent -> {}
            is DoctorContract.Event.ShowDoctorCases ->
               setProfileUi(event.modelDoctorCases.data)
        }
    }

    private fun handleStates(state: DoctorContract.State?) {
        when (state) {
            is DoctorContract.State.ShowErrorMessage -> showMessage(state.uiMessage)
            is DoctorContract.State.ShowThrowableMessage -> showMessage(state.throwable.message)
            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun setProfileUi(doctorCasesData: List<CasesItem?>?) {
        binding.rvCases.adapter = adapterDoctorCases
        adapterDoctorCases.submitList(doctorCasesData)
    }
}
