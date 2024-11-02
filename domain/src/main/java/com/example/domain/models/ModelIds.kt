package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelIds(
    val caseId: Int?,
    val nurseId: Int?,
): Parcelable