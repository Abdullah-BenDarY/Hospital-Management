package com.example.domain.useCases

import com.example.domain.repoisitories.HrRepo
import javax.inject.Inject

class HrUseCase @Inject constructor(private val hrRepo: HrRepo) {

    fun invoke(type: String) = hrRepo.getAllUsers(type)

    fun createUser(
        email: String, password: String, fName: String, lName: String,
        gender: String, specialist: String, birthday: String, status: String,
        address: String, mobile: String, type: String
    ) = hrRepo.createUser(
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