package com.example.hospitalmanagement.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentProfileBinding
import com.example.hospitalmanagement.ui.login.LoginFragmentDirections
import com.example.hospitalmanagement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileContract.ViewModel>() {
    private val profileViewModel: ProfileContract.ViewModel by viewModels<ProfileViewModel>()
    private val args by navArgs<ProfileFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun initViewModel(): ProfileContract.ViewModel {
        return profileViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.doIntent(ProfileContract.Intent.GetProfile(args.userId))
        observeProfile()
        initClicks()
    }

    private fun initClicks() {

    }

    private fun setProfileUi(modelLogin: ModelLogin) {
        val data = modelLogin.data
        showToast(data?.id)
        binding.apply {

        }
    }

    private fun observeProfile() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: ProfileContract.Event) {
        when (event) {
            ProfileContract.Event.InitialEvent -> {}
            is ProfileContract.Event.ShowData -> setProfileUi(event.modelLogin)
            is ProfileContract.Event.NavigateToEdit -> {
                findNavController().navigate(
                    LoginFragmentDirections.globalActionToHomeFragment(
                        event.modelLogin
                    )
                )
        }
        }
    }

    private fun handleStates(state: ProfileContract.State?) {
        when (state) {
            is ProfileContract.State.ShowErrorMessage -> showToast(state.uiMessage)
            is ProfileContract.State.ShowThrowableMessage -> showToast(state.throwable.message)
            null -> showToast(getString(R.string.something_went_wrong))
        }
    }
}