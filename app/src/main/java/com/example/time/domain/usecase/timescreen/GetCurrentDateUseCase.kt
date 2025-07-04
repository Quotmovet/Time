package com.example.time.domain.usecase.timescreen

import com.example.time.domain.repositories.timescreen.CurrentTimeRepository
import javax.inject.Inject

class GetCurrentDateUseCase
    @Inject
    constructor(
        private val currentTimeRepository: CurrentTimeRepository,
    ) {
        operator fun invoke(): String = currentTimeRepository.getCurrentDate()
    }
