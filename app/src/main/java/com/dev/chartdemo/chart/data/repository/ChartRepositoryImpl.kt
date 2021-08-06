package com.dev.chartdemo.chart.data.repository

import com.dev.chartdemo.chart.data.dto.ChartDataResponseDto
import com.dev.chartdemo.chart.data.mapper.ChartDataToFaceMapper
import com.dev.chartdemo.chart.domain.face.ChartDataFace
import com.dev.chartdemo.chart.domain.repository.ChartRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
class ChartRepositoryImpl(
    private val dataSource: ChartDataSource,
    private val mapper: ChartDataToFaceMapper,
    private val moshi: Moshi,
    private val coroutineContext: CoroutineContext
) : ChartRepository {
    override suspend fun getChartData(): Result<List<ChartDataFace>> =
        withContext(coroutineContext) {
            try {
                val response = dataSource.getChartWeeklyData()
                val adapter = moshi.adapter(ChartDataResponseDto::class.java)
                val result = adapter.fromJson(response)!!
                val resultFace = mapper.map(result)
                resultFace
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }
}