package com.dev.chartdemo.chart.domain

import com.dev.chartdemo.chart.domain.repository.ChartRepository
import com.github.mikephil.charting.data.CandleEntry
import java.lang.Exception

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
class GetWeeklyChartDataUseCase(
    private val chartRepository: ChartRepository,
) {
    suspend fun execute(): Result<List<CandleEntry>> {
        val chartData = chartRepository.getChartData()
        if (chartData.isSuccess) {
            val candleList = ArrayList<CandleEntry>()
            chartData.getOrDefault(emptyList()).forEachIndexed { i, chartDataFace ->
                chartDataFace.date.forEachIndexed { j, s ->
                    val entry = CandleEntry(
                        j.toFloat(),
                        chartDataFace.highs[j],
                        chartDataFace.lows[j],
                        chartDataFace.opens[j],
                        chartDataFace.closures[j]
                    )
                    candleList.add(entry)
                }
            }
            return Result.success(candleList)
        }
        return Result.failure(chartData.exceptionOrNull() ?: Exception())
    }
}