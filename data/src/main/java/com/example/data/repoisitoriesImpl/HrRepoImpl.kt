package com.example.data.repoisitoriesImpl

import com.example.data.data.dataSource.dataSourcesContract.HrDataSource
import com.example.domain.ApiResult
import com.example.domain.models.ModelAllUsers
import com.example.domain.models.ModelLogin
import com.example.domain.repoisitories.HrRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HrRepoImpl @Inject constructor(
    private val hrDataSource: HrDataSource
) : HrRepo {
    override fun getAllUsers(type: String): Flow<ApiResult<ModelAllUsers>?> =
        hrDataSource.getAllUsers(type)

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
    ): Flow<ApiResult<ModelLogin>?> =
        hrDataSource.createUser(
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
        )
}