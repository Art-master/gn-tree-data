--liquibase formatted sql

--changeset goltsev_GN:create_tree_table_09.02.2023

CREATE TABLE IF NOT EXISTS tree
(
    id uuid NOT NULL,
    name text NOT NULL,
    description character varying NOT NULL,
    user_id bigint NOT NULL,
    main_tree_view_id uuid NOT NULL,
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

COMMENT ON COLUMN tree.main_tree_view_id
    IS 'Tree main view identifier';