package com.example.hospitalmanagement.ui.splash

import androidx.lifecycle.LiveData
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

interface SplashContract{
    abstract class ViewModel : BaseViewModel(){
        abstract fun doIntent(intent: Intent)
        abstract val states: LiveData<State>
        abstract val events: StateFlow<Event>
    }
    // view -> viewModel
    sealed class Intent {
        data object DoLogin:Intent()
    }

    // viewModel -> view
    sealed class State {
        data class ShowErrorMessage(val uiMessage: String) : State()
        data class ShowThrowableMessage(val throwable: Throwable) : State()
    }

    // viewModel -> view
    sealed class Event {
        data object InitialEvent : Event()
        data class NavigateToHome(val modelLogin: ModelLogin) : Event()
        data object NavigateToLogin : Event()
    }
}