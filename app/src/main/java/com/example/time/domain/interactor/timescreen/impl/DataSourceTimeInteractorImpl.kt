package com.example.time.domain.interactor.timescreen.impl

import com.example.time.domain.interactor.timescreen.DataSourceTimeInteractor
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.domain.repositories.timescreen.DataSourceTimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataSourceTimeInteractorImpl @Inject constructor (
    private val dataSourceTimeRepository: DataSourceTimeRepository
): DataSourceTimeInteractor {
    override suspend fun getDataSourceTime(expression: String): Flow<List<TimeDataModel>> {
        return dataSourceTimeRepository.getTimeData(expression)
    }
}