package com.history.tree.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EqualsAndHashCode
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
