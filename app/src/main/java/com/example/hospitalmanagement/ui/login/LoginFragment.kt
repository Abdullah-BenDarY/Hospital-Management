package com.example.hospitalmanagement.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentLoginBinding
import com.example.hospitalmanagement.utils.shakeErrorView
import com.example.hospitalmanagement.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginContract.ViewModel>() {
    private val loginViewModel: LoginContract.ViewModel by viewModels<LoginViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initViewModel(): LoginContract.ViewModel {
        return loginViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLogin()
        initClicks()
    }


     private fun initClicks() {
         setupTextWatchers()
        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.forgetPasswordBtn.setOnClickListener {
            viewModel.doIntent(LoginContract.Intent.RePassword)
        }
    }

    private fun observeLogin() {
        viewModel.states.observe(viewLifecycleOwner, this::handleStates)

        lifecycleScope.launch {
            viewModel.events.collect {
                handleEvents(it)
            }
        }
    }

    private fun handleEvents(event: LoginContract.Event) {
        when (event) {
            LoginContract.Event.InitialEvent -> {}
            is LoginContract.Event.NavigateToForgetPassword -> {}
            is LoginContract.Event.NavigateToHome -> {
                findNavController().navigate(LoginFragmentDirections.globalActionToHomeFragment(event.modelLogin))
            }
        }
    }

    private fun handleStates(state: LoginContract.State?) {
        when (state) {
            is LoginContract.State.ShowErrorMessage ->
                showMessage(state.uiMessage)

            is LoginContract.State.ShowThrowableMessage ->
                showMessage(state.throwable.message)

            null -> showMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun setupTextWatchers() {
        binding.tilEmail.editText?.addTextChangedListener {
            binding.tilEmail.error = null
        }
        binding.tilPassword.editText?.addTextChangedListener {
                binding.tilPassword.error = null
        }
    }

    private fun doLogin()  {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val login = viewModel.doIntent(LoginContract.Intent.DoLogin(email, password))

        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_is_required)
            shakeErrorView(binding.tilEmail)
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = getString(R.string.enter_a_valid_email)
            shakeErrorView(binding.tilEmail)
            return
        }
        if (password.isBlank()) {
            binding.tilPassword.error = getString(R.string.password_is_required)
            shakeErrorView(binding.tilPassword)
        } else
            return login
    }
}