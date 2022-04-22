package com.history.tree.dto;

import lombok.Data;

@Data
public class RelationshipDTO {

    private Long id;

    private MarriageDTO marriage;

    private Long relationPersonId;

    private int relationshipType;

}
