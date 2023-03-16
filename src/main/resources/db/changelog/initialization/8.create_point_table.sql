--liquibase formatted sql

--changeset goltseva_TA:create_person_view_table_11.03.2023

CREATE TABLE IF NOT EXISTS point
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    coordinate_x numeric,
	coordinate_y numeric,
	next_point_id bigint,
	connection_type integer,
	marriage_relationship_id bigint,
	tree_id bigint,
    CONSTRAINT point_pkey PRIMARY KEY (id),
    CONSTRAINT next_point_id FOREIGN KEY (next_point_id)
        REFERENCES point (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT marriage_relationship_id FOREIGN KEY (marriage_relationship_id)
        REFERENCES relationship (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE point
    IS 'Describes connected points (edges) on frontend';

COMMENT ON COLUMN point.id
    IS 'Unique identifier';

COMMENT ON COLUMN point.coordinate_x
    IS 'Coordinate X of point on frontend';

COMMENT ON COLUMN point.coordinate_y
    IS 'Coordinate Y of point on frontend';

COMMENT ON COLUMN point.next_point_id
    IS 'Next connected point identifier';

COMMENT ON COLUMN point.connection_type
    IS 'Connection type between two points (support, marriage, children)';

COMMENT ON COLUMN point.marriage_relationship_id
    IS 'For marriage connection type relationship identifier between two points';

COMMENT ON COLUMN point.tree_id
    IS 'Tree identifier';