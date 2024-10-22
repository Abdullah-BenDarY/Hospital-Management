package com.example.hospitalmanagement.ui.hr.employeeList

import androidx.lifecycle.LiveData
import com.example.domain.models.ModelAllUsers
import com.example.hospitalmanagement.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

interface EmployeeListContract {
    abstract class ViewModel : BaseViewModel() {
        abstract fun doIntent(intent: Intent)
        abstract val states: LiveData<State>
        abstract val events: StateFlow<Event>
    }

    // view -> viewModel
    sealed class Intent {
        data class getAllUsers(val type: String) : Intent()

    }

    // viewModel -> view
    sealed class State {
        data class ShowErrorMessage(val uiMessage: String) : State()
        data class ShowThrowableMessage(val throwable: Throwable) : State()
    }

    // viewModel -> view
    sealed class Event {
        data object InitialEvent : Event()
        data class ShowData(val modelAllUsers: ModelAllUsers) : Event()

    }
}