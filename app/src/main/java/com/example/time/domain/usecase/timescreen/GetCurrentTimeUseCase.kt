package com.example.time.domain.usecase.timescreen

import com.example.time.domain.repositories.timescreen.TimesRepository
import javax.inject.Inject

class GetCurrentTimeUseCase @Inject constructor(
    private val timeRepository: TimesRepository
) {
    operator fun invoke(): String = timeRepository.getCurrentTime()
}