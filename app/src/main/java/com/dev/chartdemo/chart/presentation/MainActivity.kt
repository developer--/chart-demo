package com.dev.chartdemo.chart.presentation

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.chartdemo.common.di.SampleDiProvider
import com.dev.chartdemo.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by lazy { SampleDiProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
    }

    private fun initObservers() {
        viewModel.weeklyChartData.observe(this, { result ->
            result.onSuccess { chartData: List<CandleEntry> ->
                binding.candleStickChart.resetTracking()
                val set1 = CandleDataSet(chartData, "Data")
                set1.setDrawIcons(false)
                set1.axisDependency = AxisDependency.LEFT
                set1.shadowColor = Color.DKGRAY
                set1.shadowWidth = 0.7f
                set1.decreasingColor = Color.RED
                set1.decreasingPaintStyle = Paint.Style.FILL
                set1.increasingColor = Color.rgb(122, 242, 84)
                set1.increasingPaintStyle = Paint.Style.STROKE
                set1.neutralColor = Color.BLUE
                val data = CandleData(set1)

                binding.candleStickChart.data = data
                binding.candleStickChart.invalidate()
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}