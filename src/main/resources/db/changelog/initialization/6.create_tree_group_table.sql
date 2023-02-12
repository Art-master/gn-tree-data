--liquibase formatted sql

--changeset goltsev_GN:create_tree_group_table_09.02.2023

CREATE TABLE IF NOT EXISTS tree_group
(
    id bigint NOT NULL,
    CONSTRAINT "Test_pkey" PRIMARY KEY (id)
);

COMMENT ON TABLE tree_group
    IS 'Group of trees';

COMMENT ON COLUMN tree_group.id
    IS 'Group unique identifier';
