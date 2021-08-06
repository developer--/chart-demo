package com.dev.chartdemo.chart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.chartdemo.chart.domain.GetWeeklyChartDataUseCase
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
class MainActivityViewModel(
    private val chartDataUseCase: GetWeeklyChartDataUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _weeklyChartData = MutableLiveData<Result<List<CandleEntry>>>()
    val weeklyChartData: LiveData<Result<List<CandleEntry>>> get() = _weeklyChartData

    init {
        viewModelScope.launch(coroutineDispatcher) {
            val result = chartDataUseCase.execute()
            _weeklyChartData.postValue(result)
        }
    }
}