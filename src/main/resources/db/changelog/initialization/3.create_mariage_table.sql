--liquibase formatted sql

--changeset goltsev_GN:create_marriage_table_09.02.2023

CREATE TABLE IF NOT EXISTS marriage
(
    id bigint NOT NULL,
    date_start date,
    date_end date,
    tree_id bigint,
    CONSTRAINT marriage_pkey PRIMARY KEY (id),
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
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

COMMENT ON COLUMN marriage.tree_id
    IS 'Tree identifier';