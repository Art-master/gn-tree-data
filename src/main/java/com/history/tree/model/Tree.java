package com.history.tree.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tree")
public class Tree {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

}
