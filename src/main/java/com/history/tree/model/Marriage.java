package com.history.tree.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "marriage")
public class Marriage {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "date_start")
    private String dateStart;

    @Column(name = "date_end")
    private String dateEnd;

}
