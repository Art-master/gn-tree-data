package com.history.tree.mappers;

import com.history.tree.dto.PersonDTO;
import com.history.tree.model.Person;
import com.history.tree.model.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToDto(Person person);
}
