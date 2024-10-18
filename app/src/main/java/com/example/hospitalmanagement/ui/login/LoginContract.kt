package com.example.hospitalmanagement.ui.login

import androidx.lifecycle.LiveData
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.base.BaseViewModel
import com.example.hospitalmanagement.base.UIMessage
import kotlinx.coroutines.flow.StateFlow

class LoginContract{
    abstract class ViewModel : BaseViewModel(){
        abstract fun doIntent(intent: Intent)
        abstract val events: LiveData<Event>
       abstract val states: StateFlow<State>

    }
    sealed class Intent(){
        data class DoLogin(val email:String, val password:String):Intent()
    }

    sealed class Event(){
        data class ShowErrorMessage(val uiMessage: String):Event()
        data class ShowThrowableMessage(val throwable: Throwable):Event()
    }

    sealed class State(){
        data object InitialState : State()
        class NavigateToHome( val modelLogin: ModelLogin): State()
        class NavigateToForgetPassword( val modelLogin: ModelLogin): State()
    }
}