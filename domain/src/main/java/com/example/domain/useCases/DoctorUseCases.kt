package com.example.domain.useCases

import com.example.domain.repoisitories.DoctorRepo
import javax.inject.Inject

class DoctorUseCases@Inject constructor(private val doctorRepo: DoctorRepo) {

    fun invoke() = doctorRepo.getAllCalls()
}