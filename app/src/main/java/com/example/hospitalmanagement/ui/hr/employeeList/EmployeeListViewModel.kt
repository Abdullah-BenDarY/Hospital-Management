package com.example.hospitalmanagement.ui.hr.employeeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResult
import com.example.domain.useCases.AllUsersUseCase
import com.example.hospitalmanagement.ui.profile.ProfileContract
import com.example.myapp.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val allUsersUseCase: AllUsersUseCase
) : EmployeeListContract.ViewModel() {

    private val _state = SingleLiveData<EmployeeListContract.State>()
    private val _event =
        MutableStateFlow<EmployeeListContract.Event>(EmployeeListContract.Event.InitialEvent)

    override val states: LiveData<EmployeeListContract.State> get() = _state
    override val events: StateFlow<EmployeeListContract.Event> get() = _event

    override fun doIntent(intent: EmployeeListContract.Intent) {
        when (intent) {
            is EmployeeListContract.Intent.getAllUsers -> {
                getAllUsers(intent.type)
            }
        }
    }

    private fun getAllUsers(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            allUsersUseCase.invoke(type)
                .collect { result ->
                    when (result) {
                        is ApiResult.Success ->
                            if (result.data.status == 1) {
                                _event.emit(
                                    EmployeeListContract.Event.ShowData(
                                        result.data
                                    )
                                )
                            } else _state.postValue(
                                EmployeeListContract.State.ShowErrorMessage(
                                    result.data.message ?: "Unkonwn Error"
                                )
                            )

                        is ApiResult.Failure -> _state.postValue(
                            EmployeeListContract.State.ShowThrowableMessage(
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