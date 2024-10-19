package com.example.hospitalmanagement.ui.login

import androidx.lifecycle.LiveData
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

interface LoginContract{
    abstract class ViewModel : BaseViewModel(){
        abstract fun doIntent(intent: Intent)
        abstract val states: LiveData<State>
        abstract val events: StateFlow<Event>
    }
    // view -> viewModel
    sealed class Intent {
        data class DoLogin(val email:String, val password:String):Intent()
        data object RePassword : Intent()
    }

    // viewModel -> view
    sealed class State {
        data class ShowErrorMessage(val uiMessage: String) : State()
        data class ShowThrowableMessage(val throwable: Throwable) : State()
    }

    // viewModel -> view
    sealed class Event {
        data object InitialEvent : Event()
        class NavigateToHome(val modelLogin: ModelLogin) : Event()
        class NavigateToForgetPassword(val modelLogin: ModelLogin) : Event()
    }
}