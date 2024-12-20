package com.example.domain.useCases

import com.example.domain.repoisitories.DoctorRepo
import javax.inject.Inject

class DoctorUseCases@Inject constructor(private val doctorRepo: DoctorRepo) {
    fun invoke() = doctorRepo.getAllCalls()
    fun acceptRejectCall(id: Int, status: String) = doctorRepo.acceptRejectCall(id = id, status = status)
    fun invokeEndCase(id: Int) = doctorRepo.endCase(id)
    fun invokeDoctorCases() = doctorRepo.getAllCases()
    fun invokeCaseDetails(id: Int) = doctorRepo.getCaseDetails(id)
    fun getNurseList()= doctorRepo.getNurseList()
    fun setNurse(callId: Int?, userId: Int?) = doctorRepo.setNurse(callId = callId, userId = userId)
    fun makeRequest(
        callId: Int,
        userId: Int,
        note: String?,
        type0: String,
        type1: String?,
        type2: String?
    ) = doctorRepo.makeRequest(
        callId = callId,
        userId = userId,
        note = note,
        type0 = type0,
        type1 = type1,
        type2 = type2
    )

}