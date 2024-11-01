package com.example.hospitalmanagement.ui.doctor.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.DoctorUseCases
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
    val sharedData = MutableLiveData<Int>()
    private val _state = SingleLiveData<DoctorContract.State>()
    private val _event = MutableStateFlow<DoctorContract.Event>(DoctorContract.Event.InitialEvent)

    override val states: LiveData<DoctorContract.State> get() = _state
    override val events: StateFlow<DoctorContract.Event> get() = _event

    override fun doIntent(intent: DoctorContract.Intent) {
        when (intent) {
            is DoctorContract.Intent.GetAllCalls -> getAllCalls()
            is DoctorContract.Intent.AcceptOrRejectCall -> acceptOrRejectCall(intent.id, intent.status)
            is DoctorContract.Intent.GetDoctorCases -> getDoctorCases()
            is DoctorContract.Intent.GetCaseDetails -> getCaseDetails(intent.id)
            is DoctorContract.Intent.EndCase -> endCase(intent.id)
            is DoctorContract.Intent.GetNurseList -> getNursesList()
            is DoctorContract.Intent.SetNurse -> setNurse(intent.callId, intent.userId)
            is DoctorContract.Intent.HitRequest -> hitRequest(
                callId = intent.callId,
                userId = intent.userId,
                note = intent.note,
                type0 = intent.type0,
                type1 = intent.type1,
                type2 = intent.type2
            )
        }
    }

    private fun hitRequest(
        callId: Int,
        userId: Int,
        note: String?,
        type0: String,
        type1: String?,
        type2: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            doctorUseCases.makeRequest(
                callId = callId,
                userId = userId,
                note = note,
                type0 = type0,
                type1 = type1,
                type2 = type2
            )
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowRequest
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

    private fun endCase(id: Int) {
        viewModelScope.launch (Dispatchers.IO){
            doctorUseCases.invokeEndCase(id)
                .collect{result ->
                    when(result){
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.CaseEnded
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

    private fun getCaseDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            doctorUseCases.invokeCaseDetails(id)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowCaseData(
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

    private fun setNurse(callId: Int?, userId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            doctorUseCases.setNurse(callId, userId)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.AddNurse,
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

    private fun getNursesList() {
        viewModelScope.launch(Dispatchers.IO) {
            doctorUseCases.getNurseList()
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    DoctorContract.Event.ShowNurseList(
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