package com.history.tree.model;


import lombok.Data;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "person")
public class Person {

    @Id
    @Column(name ="id")
    private Long id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="patronymic")
    private String patronymic;

    @Column(name ="tree_id")
    private Long treeId;

    @Column(name ="gender")
    private Character gender;

    @Column(name ="birth_date")
    private LocalDate birthDate;

    @Column(name ="date_of_death")
    private LocalDate dateOfDeath;

    private Set<Relationship> relationships = new HashSet<>();

}
