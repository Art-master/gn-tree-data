package com.history.tree.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tree")
public class Tree {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
