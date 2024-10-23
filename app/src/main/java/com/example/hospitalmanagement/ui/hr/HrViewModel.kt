package com.example.hospitalmanagement.ui.hr

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.HrUseCase
import com.example.hospitalmanagement.ui.login.LoginContract
import com.example.hospitalmanagement.ui.profile.ProfileContract
import com.example.myapp.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HrViewModel @Inject constructor(
    private val hrUseCase: HrUseCase
) : HrContract.ViewModel() {

    private val _state = SingleLiveData<HrContract.State>()
    private val _event =
        MutableStateFlow<HrContract.Event>(HrContract.Event.InitialEvent)

    override val states: LiveData<HrContract.State> get() = _state
    override val events: StateFlow<HrContract.Event> get() = _event

    override fun doIntent(intent: HrContract.Intent) {
        when (intent) {
            is HrContract.Intent.getAllUsers -> {
                getAllUsers(intent.type)
            }

            is HrContract.Intent.createUser -> createUser(
                intent.email,
                intent.password,
                intent.fName,
                intent.lName,
                intent.gender,
                intent.specialist,
                intent.birthday,
                intent.status,
                intent.address,
                intent.mobile,
                intent.type
            )
        }
    }

    private fun getAllUsers(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            hrUseCase.invoke(type)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    HrContract.Event.ShowData(
                                        result.data
                                    )
                                )
                            } else _state.postValue(
                                HrContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            HrContract.State.ShowThrowableMessage(
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

    private fun createUser(
        email: String, password: String, fName: String, lName: String,
        gender: String, specialist: String, birthday: String, status: String,
        address: String, mobile: String, type: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            hrUseCase.createUser(
                email,
                password,
                fName,
                lName,
                gender,
                specialist,
                birthday,
                status,
                address,
                mobile,
                type
            )
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    HrContract.Event.ShowNewUserResponse(
                                        message = result.data.message

                                    )
                                )
                            } else _state.postValue(
                                HrContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            HrContract.State.ShowThrowableMessage(
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