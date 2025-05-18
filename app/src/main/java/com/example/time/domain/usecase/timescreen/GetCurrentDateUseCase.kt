package com.example.time.domain.usecase.timescreen

import com.example.time.domain.repositories.timescreen.TimesRepository
import javax.inject.Inject

class GetCurrentDateUseCase @Inject constructor(
    private val timesRepository: TimesRepository
) {
    operator fun invoke(): String = timesRepository.getCurrentDate()
}