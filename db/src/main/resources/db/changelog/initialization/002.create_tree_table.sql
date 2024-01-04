--liquibase formatted sql

--changeset goltsev_GN:create_tree_table_09.02.2023

CREATE TABLE IF NOT EXISTS tree
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name text NOT NULL,
    description character varying,
    user_id bigint NOT NULL,
    CONSTRAINT tree_pkey PRIMARY KEY (id)
);

COMMENT ON TABLE tree
    IS 'Stores information about a specific tree';

COMMENT ON COLUMN tree.id
    IS 'Tree unique identifier';

COMMENT ON COLUMN tree.name
    IS 'Tree name';

COMMENT ON COLUMN tree.description
    IS 'Tree description';