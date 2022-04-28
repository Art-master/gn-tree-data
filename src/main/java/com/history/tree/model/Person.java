package com.history.tree.model;


import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@Table("person")
public class Person {

    @Id
    @Column("id")
    private Long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("patronymic")
    private String patronymic;

    @Column("tree_id")
    private Long treeId;

    @Column("gender")
    private Character gender;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("date_of_death")
    private LocalDate dateOfDeath;

}
