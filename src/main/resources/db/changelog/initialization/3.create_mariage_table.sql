--liquibase formatted sql

--changeset goltsev_GN:create_marriage_table_09.02.2023

CREATE TABLE IF NOT EXISTS marriage
(
    id bigint NOT NULL,
    date_start date,
    date_end date,
    CONSTRAINT marriage_pkey PRIMARY KEY (id)
);

COMMENT ON TABLE marriage
    IS 'Information about marriage';

COMMENT ON COLUMN marriage.id
    IS 'Marriage identifier';

COMMENT ON COLUMN marriage.date_start
    IS 'Marriage start date';

COMMENT ON COLUMN marriage.date_end
    IS 'Marriage end date';
