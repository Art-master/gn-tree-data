--liquibase formatted sql

--changeset goltsev_GN:create_relationship_table_09.02.2023

CREATE TABLE IF NOT EXISTS relationship
(
    id uuid NOT NULL,
    person_id uuid NOT NULL,
    related_person_id uuid NOT NULL,
    relationship_type smallint NOT NULL,
    marriage_id uuid,
    tree_view_id uuid,
    CONSTRAINT relationship_pkey PRIMARY KEY (id),
    CONSTRAINT marriage_key FOREIGN KEY (marriage_id)
        REFERENCES marriage (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT person_key FOREIGN KEY (person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT related_person_key FOREIGN KEY (related_person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT tree_view_id FOREIGN KEY (tree_view_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE relationship
    IS 'Describes the role of each person';

COMMENT ON COLUMN relationship.id
    IS 'Unique relationship identifier';

COMMENT ON COLUMN relationship.person_id
    IS 'Person identifier';

COMMENT ON COLUMN relationship.related_person_id
    IS 'Second person identifier';

COMMENT ON COLUMN relationship.relationship_type
    IS 'Relationship type';

COMMENT ON COLUMN relationship.marriage_id
    IS 'Mariage identifier';

COMMENT ON COLUMN relationship.tree_view_id
    IS 'Tree view identifier';