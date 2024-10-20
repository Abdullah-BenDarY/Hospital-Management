package com.example.hospitalmanagement.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.LoginUseCase
import com.example.domain.useCases.ProfileUseCase
import com.example.myapp.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ProfileContract.ViewModel() {

    private val _state = SingleLiveData<ProfileContract.State>()
    private val _event = MutableStateFlow<ProfileContract.Event>(ProfileContract.Event.InitialEvent)

    override val states: LiveData<ProfileContract.State> get() = _state
    override val events: StateFlow<ProfileContract.Event> get() = _event

    override fun doIntent(intent: ProfileContract.Intent) {
        when (intent) {
            is ProfileContract.Intent.GetProfile -> {
                getProfileDetails(intent.id)
            }
        }
    }

    private fun getProfileDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            profileUseCase.invoke(id)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _state.postValue(
                                    ProfileContract.State.ShowData(
                                        result.data
                                    )
                                )
                                _event.emit(
                                    ProfileContract.Event.NavigateToEdit(
                                        modelLogin = result.data
                                    )
                                )
                            } else _state.postValue(
                                ProfileContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            ProfileContract.State.ShowThrowableMessage(
                                result.throwable
                            )
                        )

                        null -> ProfileContract.State.ShowErrorMessage(
                            "Unkonwn Error"
                        )
                    }

                }
        }
    }
}