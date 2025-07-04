package com.example.time.domain.interactor.timescreen

import com.example.time.domain.model.timescreen.TimeDataModel
import kotlinx.coroutines.flow.Flow

interface DataSourceTimeInteractor {
    suspend fun getDataSourceTime(expression: String): Flow<List<TimeDataModel>>
}
