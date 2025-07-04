package com.example.time.data.dto.timescreen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeDataDto(
    @SerializedName("hour") val hour: Int,
    @SerializedName("minute") val minute: Int,
    @SerializedName("timezone") val timeZone: String,
) : Parcelable
