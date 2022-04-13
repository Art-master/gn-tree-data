package com.history.tree.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "relationship")
public class Relationship {

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
