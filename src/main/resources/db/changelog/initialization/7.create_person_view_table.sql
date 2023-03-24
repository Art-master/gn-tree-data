--liquibase formatted sql

--changeset goltseva_TA:create_person_view_table_11.03.2023

CREATE TABLE IF NOT EXISTS person_view
(
    id uuid NOT NULL,
    coordinate_x numeric,
	coordinate_y numeric,
	person_id uuid,
	tree_id uuid,
    CONSTRAINT person_view_pkey PRIMARY KEY (id),
    CONSTRAINT person_id FOREIGN KEY (person_id)
        REFERENCES person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE person_view
    IS 'Stores info about each person on frontend';

COMMENT ON COLUMN person_view.id
    IS 'Unique identifier';

COMMENT ON COLUMN person_view.coordinate_x
    IS 'Coordinate X of person icon on frontend';

COMMENT ON COLUMN person_view.coordinate_y
    IS 'Coordinate Y of person icon on frontend';

COMMENT ON COLUMN person_view.person_id
    IS 'Person identifier';

COMMENT ON COLUMN person_view.tree_id
    IS 'Tree identifier';