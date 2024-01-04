package com.history.tree.model

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter

enum class RelationshipType(val code: Short) {
    MARRIAGE(1), EX_MARRIAGE(2), PARENT(3)
}

@WritingConverter
class RelationshipTypeToIntConverter : Converter<RelationshipType, Short> {
    override fun convert(source: RelationshipType): Short = source.code
}

@ReadingConverter
class IntToRelationshipTypeConverter : Converter<Short, RelationshipType> {
    override fun convert(source: Short): RelationshipType {
        return RelationshipType.values().first { p -> p.code == source }
    }
}