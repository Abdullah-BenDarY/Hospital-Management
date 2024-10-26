package com.example.data.dataSource.dataSourcesImpl

import com.example.data.apiCalls.WebServices
import com.example.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelLogin
import executeApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HrDataSourceImpl @Inject constructor
    (private val apiService: WebServices) : HrDataSource {

    override fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?> {
        return executeApi {
            apiService.getAllUsers(type).toModelAllUsers()
        }
    }

    override fun createUser(
        email: String,
        password: String,
        fName: String,
        lName: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String
    ): Flow<ApiResult<ModelLogin>?> {
        return executeApi {
            apiService.registerUser(
                email,
                password,
                fName,
                lName,
                gender,
                specialist,
                birthday,
                status,
                address,
                mobile,
                type
            ).toModelLogin()
        }
    }
}