--liquibase formatted sql

--changeset goltseva_TA:create_person_view_table_12.03.2023

CREATE TABLE IF NOT EXISTS related_points
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
	person_view_id bigint,
	point_id bigint,
	tree_id bigint,
    CONSTRAINT related_points_pkey PRIMARY KEY (id),
    CONSTRAINT person_view_id FOREIGN KEY (person_view_id)
        REFERENCES person_view (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT point_id FOREIGN KEY (point_id)
        REFERENCES point (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE related_points
    IS 'Stores points containing info about marriage or children related with each person';

COMMENT ON COLUMN related_points.id
    IS 'Unique identifier';

COMMENT ON COLUMN related_points.person_view_id
    IS 'Person view identifier';

COMMENT ON COLUMN related_points.point_id
    IS 'Identifier of a point containing info about marriage or children related with the person';

COMMENT ON COLUMN related_points.tree_id
    IS 'Tree identifier';