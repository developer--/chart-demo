package com.dev.chartdemo.chart.domain.repository

import com.dev.chartdemo.chart.domain.face.ChartDataFace

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
interface ChartRepository {
    suspend fun getChartData(): Result<List<ChartDataFace>>
}