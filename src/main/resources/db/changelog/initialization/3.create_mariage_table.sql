--liquibase formatted sql

--changeset goltsev_GN:create_marriage_table_09.02.2023

CREATE TABLE IF NOT EXISTS marriage
(
    id uuid NOT NULL,
    date_start date,
    date_end date,
    tree_view_id uuid,
    CONSTRAINT marriage_pkey PRIMARY KEY (id),
    CONSTRAINT tree_view_id FOREIGN KEY (tree_view_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE marriage
    IS 'Information about marriage';

COMMENT ON COLUMN marriage.id
    IS 'Marriage identifier';

COMMENT ON COLUMN marriage.date_start
    IS 'Marriage start date';

COMMENT ON COLUMN marriage.date_end
    IS 'Marriage end date';

COMMENT ON COLUMN marriage.tree_view_id
    IS 'Tree view identifier';