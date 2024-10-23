package com.example.hospitalmanagement.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.data.model.ModelDoctorCallsDTO
import com.example.domain.models.DoctorCallsData
import com.example.domain.models.ModelDoctorCalls
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.adapters.AdapterDoctorCalls
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.databinding.FragmentDoctorCallsBinding
import com.example.hospitalmanagement.ui.home.HomeFragmentArgs
import com.example.hospitalmanagement.ui.profile.ProfileContract
import com.example.hospitalmanagement.utils.ACCEPTED
import com.example.hospitalmanagement.utils.REJECTED
import com.example.hospitalmanagement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class DoctorCallsFragment : BaseFragment<FragmentDoctorCallsBinding , DoctorContract.ViewModel>() {
    private val adapterDoctorCalls = AdapterDoctorCalls()
    private val doctorViewModel: DoctorContract.ViewModel by viewModels<DoctorViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoctorCallsBinding.inflate(inflater, container, false)

    override fun initViewModel(): DoctorContract.ViewModel {
        return doctorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorViewModel.doIntent(DoctorContract.Intent.GetAllCalls)
        observeDoctorCalls()
        initClicks()
    }

    private fun initClicks() {
        binding.apply {
            btnBack.setOnClickListener{
                findNavController().navigateUp()
            }
        }
        adapterDoctorCalls.setOnAcceptClick {
            viewModel.doIntent(DoctorContract.Intent.AcceptOrRejectCall(it , ACCEPTED))
            observeDoctorCalls(it)
        }
        adapterDoctorCalls.setOnBussyClick {
            viewModel.doIntent(DoctorContract.Intent.AcceptOrRejectCall(it , REJECTED))
            observeDoctorCalls(it)
        }
    }

    private fun observeDoctorCalls(id :Int ?= null) {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it, id!!)
            }
        }
    }

    private fun handleEvents(event: DoctorContract.Event , id :Int) {
        when (event) {
            DoctorContract.Event.InitialEvent -> {}
            is DoctorContract.Event.ShowCallsDataData ->
                setProfileUi(event.modelDoctorCalls.data)

            is DoctorContract.Event.ShowCallStatus ->
                adapterDoctorCalls.checkStatusAndRemoveItem(
                    status = event.modelCallsResponse.status , id=id)
        }
    }

    private fun handleStates(state: DoctorContract.State?) {
        when (state) {
            is DoctorContract.State.ShowErrorMessage -> showToast(state.uiMessage)
            is DoctorContract.State.ShowThrowableMessage -> showToast(state.throwable.message)
            null -> showToast(getString(R.string.something_went_wrong))
        }
    }

    private fun setProfileUi(doctorCallsData: List<DoctorCallsData?>?) {
        binding.rvCalls.adapter = adapterDoctorCalls
        adapterDoctorCalls.submitList(doctorCallsData?.toMutableList())
    }
}