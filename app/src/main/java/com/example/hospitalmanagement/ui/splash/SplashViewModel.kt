package com.example.hospitalmanagement.ui.splash

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
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : SplashContract.ViewModel() {

    private val _state = SingleLiveData<SplashContract.State>()
    private val _event = MutableStateFlow<SplashContract.Event>(SplashContract.Event.InitialEvent)

    override val states: LiveData<SplashContract.State> get() = _state
    override val events: StateFlow<SplashContract.Event> get() = _event

    override fun doIntent(intent: SplashContract.Intent) {
        when (intent) {
            is SplashContract.Intent.DoLogin -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loginUseCase.invoke().collect { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            result.data.data?.accessToken?.let { accessToken ->
                                _event.emit(SplashContract.Event.NavigateToHome(modelLogin = result.data))
                            } ?: _event.emit(SplashContract.Event.NavigateToLogin)
                        }

                        is ApiResult.Failure -> {
                            _state.postValue(SplashContract.State.ShowThrowableMessage(result.throwable))
                        }

                    }
                }
            } catch (e: Exception) {
                _state.postValue(SplashContract.State.ShowThrowableMessage(e))
            }
        }
    }
}
