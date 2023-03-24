--liquibase formatted sql

--changeset goltseva_TA:create_edge_table_23.03.2023

CREATE TABLE IF NOT EXISTS edge
(
    id uuid NOT NULL,
    start_coordinate_x numeric,
	start_coordinate_y numeric,
	end_coordinate_x numeric,
    end_coordinate_y numeric,
	edge_type integer,
	tree_id uuid,
    CONSTRAINT edge_pkey PRIMARY KEY (id),
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE edge
    IS 'Describes tree edges on frontend';

COMMENT ON COLUMN edge.id
    IS 'Unique identifier';

COMMENT ON COLUMN edge.start_coordinate_x
    IS 'Coordinate X of edge start on frontend';

COMMENT ON COLUMN edge.start_coordinate_y
    IS 'Coordinate Y of edge start on frontend';

COMMENT ON COLUMN edge.end_coordinate_x
    IS 'Coordinate X of edge end on frontend';

COMMENT ON COLUMN edge.end_coordinate_y
    IS 'Coordinate Y of edge end on frontend';

COMMENT ON COLUMN edge.edge_type
    IS 'Edge type (for example: support, marriage, child)';

COMMENT ON COLUMN edge.tree_id
    IS 'Tree identifier';