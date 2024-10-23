package com.example.hospitalmanagement.ui.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.DoctorUseCases
import com.example.domain.useCases.ProfileUseCase
import com.example.myapp.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val doctorUseCases: DoctorUseCases
) : DoctorContract.ViewModel() {

    private val _state = SingleLiveData<DoctorContract.State>()
    private val _event = MutableStateFlow<DoctorContract.Event>(DoctorContract.Event.InitialEvent)

    override val states: LiveData<DoctorContract.State> get() = _state
    override val events: StateFlow<DoctorContract.Event> get() = _event

    override fun doIntent(intent: DoctorContract.Intent) {
        when (intent) {
            is DoctorContract.Intent.GetAllCalls -> getAllCalls()
            is DoctorContract.Intent.AcceptOrRejectCall -> acceptOrRejectCall(intent.id, intent.status)
            DoctorContract.Intent.GetDoctorCases -> getDoctorCases()
        }
    }

    private fun getAllCalls() {
        viewModelScope.launch(Dispatchers.IO) {
            doctorUseCases.invoke()
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowCallsDataData(
                                        result.data
                                    )
                                )
                            } else _state.postValue(
                                DoctorContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            DoctorContract.State.ShowThrowableMessage(
                                result.throwable
                            )
                        )

                        null -> DoctorContract.State.ShowErrorMessage(
                            "Unkonwn Error"
                        )
                    }

                }
        }
    }

    private fun acceptOrRejectCall(id: Int , status: String) {
        viewModelScope.launch(Dispatchers.IO){
            doctorUseCases.acceptRejectCall(id, status)
                .collect{result ->
                    when(result){
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowCallStatus(
                                        result.data
                                    )
                                )
                            } else _state.postValue(
                                DoctorContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            DoctorContract.State.ShowThrowableMessage(
                                result.throwable
                            )
                        )

                        null -> DoctorContract.State.ShowErrorMessage(
                            "Unkonwn Error"
                        )
                    }

                }
        }
    }

    private fun getDoctorCases() {
        viewModelScope.launch (Dispatchers.IO){
            doctorUseCases.invokeDoctorCases()
                .collect{ result ->
                    when(result){
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowDoctorCases(
                                        result.data
                                    )
                                )
                            } else _state.postValue(
                                DoctorContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            DoctorContract.State.ShowThrowableMessage(
                                result.throwable
                            )
                        )

                        null -> DoctorContract.State.ShowErrorMessage(
                            "Unkonwn Error"
                        )
                    }
                }

        }
    }
}