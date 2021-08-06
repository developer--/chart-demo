package com.dev.chartdemo.common.di

import com.dev.chartdemo.chart.data.mapper.ChartDataToFaceMapper
import com.dev.chartdemo.chart.data.repository.ChartDataSource
import com.dev.chartdemo.chart.data.repository.ChartRepositoryImpl
import com.dev.chartdemo.chart.domain.GetWeeklyChartDataUseCase
import com.dev.chartdemo.chart.domain.repository.ChartRepository
import com.dev.chartdemo.chart.presentation.MainActivityViewModel
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
object SampleDiProvider {

    val diMap: Map<String, Any> get() = _diMap
    private val _diMap = HashMap<String, Any>()

    fun inject() {
        _diMap.clear()
        val dataSource = getChartDataSource()
        val dispatcher = getBackgroundDispatcher()
        val moshi = getMoshi()
        val dataToFaceMapper = getChartDataFaceMapper()
        val chartRepository = getChartRepository(
            chartDataSource = dataSource,
            mapper = dataToFaceMapper,
            moshi = moshi,
            coroutineContext = dispatcher
        )
        val weeklyChartDataUseCase = getWeeklyChartDataUseCase(chartRepository)
        val viewModel =
            getMainActivityViewModel(chartDataUseCase = weeklyChartDataUseCase, dispatcher)

        _diMap[ChartDataSource::class.java.name] = dataSource
        _diMap[CoroutineDispatcher::class.java.name] = dispatcher
        _diMap[Moshi::class.java.name] = moshi
        _diMap[ChartDataToFaceMapper::class.java.name] = dataToFaceMapper
        _diMap[ChartRepositoryImpl::class.java.name] = chartRepository
        _diMap[GetWeeklyChartDataUseCase::class.java.name] = weeklyChartDataUseCase
        _diMap[MainActivityViewModel::class.java.name] = viewModel
    }

    inline fun <reified T> get(): T {
        return diMap[T::class.java.name] as T
    }

    private fun getMainActivityViewModel(
        chartDataUseCase: GetWeeklyChartDataUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ): MainActivityViewModel {
        var cached = _diMap[MainActivityViewModel::class.java.name] as? MainActivityViewModel?
        if (cached == null) {
            cached = MainActivityViewModel(chartDataUseCase, coroutineDispatcher)
        }
        return cached
    }

    private fun getChartRepository(
        chartDataSource: ChartDataSource,
        mapper: ChartDataToFaceMapper,
        moshi: Moshi,
        coroutineContext: CoroutineContext
    ): ChartRepository {
        var cached = _diMap[ChartRepositoryImpl::class.java.name] as? ChartRepositoryImpl?
        if (cached == null) {
            cached = ChartRepositoryImpl(chartDataSource, mapper, moshi, coroutineContext)
            _diMap[ChartRepositoryImpl::class.java.name] = cached
        }
        return cached
    }

    private fun getChartDataSource(): ChartDataSource {
        var cached = _diMap[ChartDataSource::class.java.name] as? ChartDataSource?
        if (cached == null) {
            cached = ChartDataSource()
            _diMap[ChartDataSource::class.java.name] = cached
        }
        return cached
    }

    private fun getChartDataFaceMapper(): ChartDataToFaceMapper {
        var cached = _diMap[ChartDataToFaceMapper::class.java.name] as? ChartDataToFaceMapper?
        if (cached == null) {
            cached = ChartDataToFaceMapper()
            _diMap[ChartDataToFaceMapper::class.java.name] = cached
        }
        return cached
    }

    private fun getMoshi(): Moshi {
        var cached = _diMap[Moshi::class.java.name] as? Moshi?
        if (cached == null) {
            cached = Moshi.Builder().build()
            _diMap[Moshi::class.java.name] = cached
        }
        return cached!!
    }

    private fun getBackgroundDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    private fun getWeeklyChartDataUseCase(chartDataRepository: ChartRepository):
            GetWeeklyChartDataUseCase {
        var cached =
            _diMap[GetWeeklyChartDataUseCase::class.java.name] as? GetWeeklyChartDataUseCase?
        if (cached == null) {
            cached = GetWeeklyChartDataUseCase(chartDataRepository)
            _diMap[GetWeeklyChartDataUseCase::class.java.name] = cached
        }
        return cached
    }
}