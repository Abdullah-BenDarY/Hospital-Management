package com.example.hospitalmanagement.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import com.example.hospitalmanagement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            is DoctorContract.Event.ShowData -> setProfileUi(event.modelDoctorCalls.data)
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