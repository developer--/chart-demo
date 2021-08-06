package com.dev.chartdemo.common.mapper

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
interface MapToFace<DTO, FACE> {
    fun map(data: DTO): Result<FACE>
}