package com.dev.chartdemo.chart.domain.face

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
data class ChartDataFace(
    val cryptoName: String,
    val date: List<String>,
    val opens: List<Float>,
    val closures: List<Float>,
    val highs: List<Float>,
    val lows: List<Float>,
    val volumes: List<Float>,
)