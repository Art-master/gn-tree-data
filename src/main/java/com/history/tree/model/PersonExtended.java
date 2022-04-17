package com.history.tree.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Data
@Entity
@Table(name = "person")
public class PersonExtended implements Serializable {

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
    private Character gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "date_of_death")
    private LocalDateTime dateOfDeath;

    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "relationship",
            joinColumns = {@JoinColumn(name = "person_id")}
    )
    private Collection<Relationship> relationships = new HashSet<>();


    @Data
    @Entity
    @Table(name = "relationship")
    public static class Relationship implements Serializable {

        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "marriage_id")
        private Long marriageId;

        @Column(name = "person_id")
        private Long personId;

        @Column(name = "relation_person_id")
        private Long relationPersonId;

        @Column(name = "relationship_type")
        private int relationshipType;

    }
}
