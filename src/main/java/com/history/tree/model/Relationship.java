package com.history.tree.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("relationship")
public class Relationship {

    @Id
    @Column("id")
    private Long id;

    @Column("marriage_id")
    private Long marriageId;

    @Column("person_id")
    private Long personId;

    @Column("relation_person_id")
    private Long relationPersonId;

    @Column("relationship_type")
    private int relationshipType;

}
