package com.example.hospitalmanagement.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hospitalmanagement.R
import com.example.hospitalmanagement.base.BaseFragment
import com.example.hospitalmanagement.databinding.FragmentLoginBinding
import com.example.hospitalmanagement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginContract.ViewModel>() {
    private val loginViewModel: LoginContract.ViewModel by viewModels<LoginViewModel>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun initViewModel(): LoginContract.ViewModel   {
        return loginViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLogin()
    }

    override fun initClicks() {
        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun observeLogin() {
        viewModel.events.observe(viewLifecycleOwner, ::handleEvents)

        lifecycleScope.launch {
            viewModel.states.collect{
                handleStates(it)
            }
        }
    }

    private fun handleStates(state: LoginContract.State): LoginContract.State {
        when(state){
            LoginContract.State.InitialState -> TODO()
            is LoginContract.State.NavigateToForgetPassword -> TODO()
            is LoginContract.State.NavigateToHome -> showToast(state.modelLogin.data?.type)
        }
        return state
    }

    private fun handleEvents(event: LoginContract.Event?) {
        when (event) {
            is LoginContract.Event.ShowMessage ->
                showToast(event.uiMessage)
            null -> showToast(getString(R.string.something_went_wrong))
        }
    }

    private fun doLogin() {
        val email = binding.etEmail .text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isBlank() ){
            binding.etEmail.error = getString(R.string.email_is_required)
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = getString(R.string.enter_a_valid_email)
            return
        }
        if (password.isBlank()){
            binding.etPassword.error = getString(R.string.password_is_required)
            return
        }
        else viewModel.doIntent(LoginContract.Intent.DoLogin(email, password))
    }
}