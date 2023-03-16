package com.history.tree.model

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

enum class RelationshipType(val code: Int) {
    MARRIAGE(1), EX_MARRIAGE(2), PARENT(3)
}

@WritingConverter
class RelationshipTypeToIntConverter : Converter<RelationshipType, Int> {
    override fun convert(source: RelationshipType): Int = source.code
}

@ReadingConverter
class IntToRelationshipTypeConverter : Converter<Int, RelationshipType> {
    override fun convert(source: Int): RelationshipType {
        return RelationshipType.values().first { p -> p.code == source }
    }
}