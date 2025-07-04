package com.example.time.domain.usecase.timescreen

import com.example.time.domain.repositories.timescreen.CurrentTimeRepository
import javax.inject.Inject

class GetCurrentTimeUseCase
    @Inject
    constructor(
        private val timeRepository: CurrentTimeRepository,
    ) {
        operator fun invoke(): String = timeRepository.getCurrentTime()
    }
