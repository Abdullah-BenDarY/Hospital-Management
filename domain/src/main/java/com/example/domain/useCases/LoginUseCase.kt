package com.example.domain.useCases

import com.example.domain.repoisitories.AuthRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepo: AuthRepo) {
    fun invoke(email: String?= null, password: String?= null) =
        authRepo.invokeLogin(email = email, password = password)

}