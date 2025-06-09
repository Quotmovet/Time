package com.example.time.data.repositories.timescreen

import com.example.time.data.datasource.timescreen.TimeZoneCsvDataSource
import com.example.time.data.datasource.timescreen.converter.toModelFromCsvDto
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.domain.repositories.timescreen.DataSourceTimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataSourceTimeRepositoryImpl @Inject constructor (
    private val timeZoneCsvDataSource: TimeZoneCsvDataSource
): DataSourceTimeRepository {
    override suspend fun getTimeData(expression: String): Flow<List<TimeDataModel>> = flow {

        val rawData = timeZoneCsvDataSource.readTimeZonesFromCsv()

        val filterData = rawData
            .filter {
            it.cityName.contains(expression, ignoreCase = true)
            }
            .map { it.toModelFromCsvDto() }

        emit(filterData)
    }.flowOn(Dispatchers.IO)
}