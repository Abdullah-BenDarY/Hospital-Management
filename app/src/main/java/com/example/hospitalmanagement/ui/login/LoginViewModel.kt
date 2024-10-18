package com.example.hospitalmanagement.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _event = SingleLiveData<LoginContract.Event>()
    private val _state = MutableStateFlow<LoginContract.State>(LoginContract.State.InitialState)

    override val events: LiveData<LoginContract.Event> get() = _event
    override val states: StateFlow<LoginContract.State> get() = _state

    override fun doIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.DoLogin -> {
                login(intent.email, intent.password)
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.invoke(email, password)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success -> _state.emit(
                            LoginContract.State.NavigateToHome(
                                modelLogin = result.data
                            )
                        )

                        is ApiResult.Failure -> _event.postValue(
                            LoginContract.Event.ShowMessage(
                                handleError(result.throwable)
                            )
                        )

                        is ApiResult.Error -> _event.postValue(
                            LoginContract.Event.ShowMessage(
                                handleError(result.message ?: "Unkonwn Error")
                            )
                        )

                        null -> handleError("Unresolved Error")
                    }

                }
        }
    }
}