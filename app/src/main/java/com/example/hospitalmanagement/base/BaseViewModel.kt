package com.example.hospitalmanagement.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.customExeption.ConnectionError
import com.example.domain.customExeption.ServerError


open class BaseViewModel:ViewModel() {


    fun handleError(message: String) : UIMessage {
        val uiMessage = MutableLiveData<UIMessage>()
        uiMessage.postValue(UIMessage(message))
        return UIMessage()
    }

    fun handleError(throwable : Throwable) : UIMessage {
        when(throwable){
            is ConnectionError -> UIMessage(throwable.message)
            is ServerError -> UIMessage(throwable.message)
            else -> UIMessage(throwable.message)
        }
        return UIMessage()
    }

}