package com.history.tree.mappers;

import com.history.tree.dto.PersonDTO;
import com.history.tree.model.Person;

//@Mapper
public interface PersonMapper {
    PersonDTO personToDto(Person person);
    Person dtoToPerson(PersonDTO personDTO);
}
