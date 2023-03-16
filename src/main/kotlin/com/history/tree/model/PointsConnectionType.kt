package com.history.tree.model

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

enum class PointsConnectionType(val code: Int) {
    SUPPORT(1), MARRIAGE(2), CHILDREN(3)
}

@WritingConverter
class PointsConnectionTypeToIntConverter : Converter<PointsConnectionType, Int> {
    override fun convert(source: PointsConnectionType): Int = source.code
}

@ReadingConverter
class IntToPointsConnectionTypeConverter : Converter<Int, PointsConnectionType> {
    override fun convert(source: Int): PointsConnectionType {
        return PointsConnectionType.values().first { p -> p.code == source }
    }
}