package com.dev.chartdemo.chart.data.mapper

import com.dev.chartdemo.chart.data.dto.ChartDataResponseDto
import com.dev.chartdemo.chart.domain.face.ChartDataFace
import com.dev.chartdemo.common.mapper.MapToFace
import java.lang.Exception
import java.util.*

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
class ChartDataToFaceMapper : MapToFace<ChartDataResponseDto, List<ChartDataFace>> {
    override fun map(data: ChartDataResponseDto): Result<List<ChartDataFace>> {
        try {
            val mappedData = data.content.quoteSymbols.map {
                ChartDataFace(
                    cryptoName = it.symbol,
                    date = it.timestamps.map { time -> time.toDate() },
                    opens = it.opens,
                    closures = it.closures,
                    highs = it.highs,
                    lows = it.lows,
                    volumes = it.volumes
                )
            }
            return Result.success(mappedData)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun Long.toDate(): String {
        val date = Date(this)
        val calendar = Calendar.getInstance().apply { this.time = date }
        val day = calendar.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT, Locale.US)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US)
        val year = calendar.getDisplayName(Calendar.YEAR, Calendar.SHORT, Locale.US)
        return "$day/$month/$year"
    }
}