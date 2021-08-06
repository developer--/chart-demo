package com.dev.chartdemo.chart.data.dto

import com.squareup.moshi.JsonClass

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
@JsonClass(generateAdapter = true)
data class ChartDataResponseDto(val content: ContentDto) {

    @JsonClass(generateAdapter = true)
    data class ContentDto(val quoteSymbols: List<QuoteSymbols>) {

        @JsonClass(generateAdapter = true)
        data class QuoteSymbols(
            val symbol: String,
            val timestamps: List<Long>,
            val opens: List<Float>,
            val closures: List<Float>,
            val highs: List<Float>,
            val lows: List<Float>,
            val volumes: List<Float>
        )
    }
}