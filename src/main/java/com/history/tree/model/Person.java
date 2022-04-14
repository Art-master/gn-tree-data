package com.history.tree.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
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
    private String tree_id;

    @Column("gender")
    private Character gender;

    @Column("birth_date")
    private LocalDateTime birthDate;

    @Column("date_of_death")
    private LocalDateTime dateOfDeath;

}
