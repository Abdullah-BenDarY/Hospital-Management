package com.example.hospitalmanagement.ui.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
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
    private val profileUseCase: ProfileUseCase
) : DoctorContract.ViewModel() {

    private val _state = SingleLiveData<DoctorContract.State>()
    private val _event = MutableStateFlow<DoctorContract.Event>(DoctorContract.Event.InitialEvent)

    override val states: LiveData<DoctorContract.State> get() = _state
    override val events: StateFlow<DoctorContract.Event> get() = _event

    override fun doIntent(intent: DoctorContract.Intent) {
        when (intent) {
            is DoctorContract.Intent.GetProfile -> {
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
                                _event.emit(
                                    DoctorContract.Event.ShowData(
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