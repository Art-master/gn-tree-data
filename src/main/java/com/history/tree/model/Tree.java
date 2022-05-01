package com.history.tree.model;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("tree")
public class Tree {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

}
