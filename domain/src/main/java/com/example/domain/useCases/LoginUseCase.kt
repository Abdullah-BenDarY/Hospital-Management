package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    fun invoke(email: String, password: String) =
        newsRepo.invokeLogin(email = email, password = password)

}