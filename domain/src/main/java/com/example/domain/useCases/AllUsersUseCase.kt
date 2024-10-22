package com.example.domain.useCases

import com.example.domain.repoisitories.HrRepo
import javax.inject.Inject

class AllUsersUseCase @Inject constructor(private val hrRepo: HrRepo) {

    fun invoke(type: String) = hrRepo.getAllUsers(type)
}