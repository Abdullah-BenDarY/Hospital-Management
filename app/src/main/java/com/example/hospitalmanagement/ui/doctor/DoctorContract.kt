package com.example.hospitalmanagement.ui.doctor

import androidx.lifecycle.LiveData
import com.example.domain.models.ModelCallsResponse
import com.example.domain.models.ModelCaseDetails
import com.example.domain.models.ModelDoctorCalls
import com.example.domain.models.ModelDoctorCases
import com.example.domain.models.ModelLogin
import com.example.hospitalmanagement.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

interface DoctorContract{
    abstract class ViewModel : BaseViewModel(){
        abstract fun doIntent(intent: Intent)
        abstract val states: LiveData<State>
        abstract val events: StateFlow<Event>
    }
    // view -> viewModel
    sealed class Intent {
        data object GetAllCalls:Intent()
        data class AcceptOrRejectCall(val id:Int, val status:String):Intent()
        data object GetDoctorCases:Intent()
        data class GetCaseDetails(val id:Int):Intent()
        data class EndCase(val id:Int):Intent()

    }

    // viewModel -> view
    sealed class State {
        data class ShowErrorMessage(val uiMessage: String) : State()
        data class ShowThrowableMessage(val throwable: Throwable) : State()
    }

    // viewModel -> view
    interface Event {
        data object InitialEvent : Event
        data class ShowCallsDataData(val modelDoctorCalls: ModelDoctorCalls) : Event
        data class ShowCallStatus(val modelCallsResponse: ModelCallsResponse) : Event
        data class ShowDoctorCases(val modelDoctorCases: ModelDoctorCases) : Event
        data class ShowCaseData(val modelCaseDetails: ModelCaseDetails) : Event
        data object CaseEnded: Event

    }
}