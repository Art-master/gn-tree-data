package com.history.tree.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Long treeId;

    private Character gender;

    private LocalDate birthDate;

    private LocalDate dateOfDeath;
}
