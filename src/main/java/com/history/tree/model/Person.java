package com.history.tree.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "tree_id")
    private String tree_id;

    @Column(name = "gender")
    private char gender;

    @Column(name = "birth_date")
    private char birthDate;

    @Column(name = "date_of_death")
    private char dateOfDeath;

}
