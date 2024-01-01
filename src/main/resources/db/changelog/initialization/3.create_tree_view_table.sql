--liquibase formatted sql

--changeset 3:goltsev_GN:create_tree_table_30.12.2023

CREATE TABLE IF NOT EXISTS tree_view
(
    id uuid NOT NULL,
    tree_id uuid NOT NULL,
    name text NOT NULL,
    description character varying NOT NULL,
    color integer NOT NULL,
    CONSTRAINT tree_view_pkey PRIMARY KEY (id),
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
         REFERENCES tree (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE CASCADE
         NOT VALID
);

COMMENT ON TABLE tree_view
    IS 'Stores information about a specific tree view';

COMMENT ON COLUMN tree_view.id
    IS 'Tree view unique identifier';

COMMENT ON COLUMN tree_view.name
    IS 'Tree view name';

COMMENT ON COLUMN tree_view.color
    IS 'Tree view common color';

COMMENT ON COLUMN tree_view.description
    IS 'Tree view description';