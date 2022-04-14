package com.history.tree.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("marriage")
public class Marriage {

    @Id
    @Column("id")
    private Long id;

    @Column("date_start")
    private String dateStart;

    @Column("date_end")
    private String dateEnd;

}
