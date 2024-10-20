package com.example.hospitalmanagement.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.LoginUseCase
import com.example.myapp.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : LoginContract.ViewModel() {

    private val _state = SingleLiveData<LoginContract.State>()
    private val _event = MutableStateFlow<LoginContract.Event>(LoginContract.Event.InitialEvent)

    override val states: LiveData<LoginContract.State> get() = _state
    override val events: StateFlow<LoginContract.Event> get() = _event

    override fun doIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.DoLogin -> {
                login(intent.email, intent.password)
            }

            LoginContract.Intent.RePassword -> {

            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.invoke(email, password)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    LoginContract.Event.NavigateToHome(
                                        modelLogin = result.data
                                    )
                                )
                            } else _state.postValue(
                                LoginContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            LoginContract.State.ShowThrowableMessage(
                                result.throwable
                            )
                        )

                        null -> LoginContract.State.ShowErrorMessage(
                            "Unkonwn Error"
                        )
                    }

                }
        }
    }
}