package com.history.tree.model

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

enum class EdgeType(val code: Int) {
    SUPPORT(1), MARRIAGE(2), CHILDREN_BRANCH(3)
}

@WritingConverter
class EdgeTypeToIntConverter : Converter<EdgeType, Int> {
    override fun convert(source: EdgeType): Int = source.code
}

@ReadingConverter
class IntToEdgeTypeConverter : Converter<Int, EdgeType> {
    override fun convert(source: Int): EdgeType {
        return EdgeType.values().first { p -> p.code == source }
    }
}