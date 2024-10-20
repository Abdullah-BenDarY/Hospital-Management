package com.example.domain.useCases

import com.example.domain.repoisitories.ProfileRepo
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepo: ProfileRepo) {

    fun invoke(id: Int) = profileRepo.getProfile(id)
}