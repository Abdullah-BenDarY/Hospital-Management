package com.example.hospitalmanagement.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentSplashBinding
import com.example.hospitalmanagement.ui.login.LoginFragmentDirections
import com.example.hospitalmanagement.utils.animateSplashImageView
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashContract.ViewModel>() {

    private val splashViewModel: SplashContract.ViewModel by viewModels<SplashViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)

    override fun initViewModel(): SplashContract.ViewModel {
        return splashViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateSplashImageView(binding.ivLogo)
        viewModel.doIntent(SplashContract.Intent.DoLogin)
        Handler(Looper.getMainLooper()).postDelayed({observeLogin()}, 3000)
    }

    private fun observeLogin() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.InitialEvent -> {}
            is SplashContract.Event.NavigateToHome -> {
                findNavController().navigate(
                    SplashFragmentDirections.globalActionToHomeFragment(
                        event.modelLogin
                    )
                )
            }

            is SplashContract.Event.NavigateToLogin -> {
                findNavController().navigate(
                    LoginFragmentDirections.globalActionToLoginFragment()
                )
            }
        }
    }

    private fun handleStates(state: SplashContract.State?) {
        when (state) {
            is SplashContract.State.ShowErrorMessage ->
                showMessage(state.uiMessage)

            is SplashContract.State.ShowThrowableMessage ->
                showMessage(state.throwable.message)

            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

}