package com.example.domain.useCases

import com.example.domain.repoisitories.DoctorRepo
import javax.inject.Inject

class DoctorUseCases@Inject constructor(private val doctorRepo: DoctorRepo) {
    fun invoke() = doctorRepo.getAllCalls()
    fun acceptRejectCall(id: Int, status: String) = doctorRepo.acceptRejectCall(id = id, status = status)
    fun invokeDoctorCases() = doctorRepo.getAllCases()
    fun invokeCaseDetails(id: Int) = doctorRepo.getCaseDetails(id)

}