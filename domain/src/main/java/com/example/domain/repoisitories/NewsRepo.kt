package com.example.domain.repoisitories

import com.example.domain.ApiResult
import com.example.domain.models.ModelLogin
import kotlinx.coroutines.flow.Flow

// contract to receive data from data layer and pass it to domain layer
interface NewsRepo {
     fun invokeLogin(email : String , password : String): Flow<ApiResult<ModelLogin>?>

}