--liquibase formatted sql

--changeset goltsev_GN:create_person_table_11.02.2023

CREATE TABLE IF NOT EXISTS person
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    first_name text,
    last_name text,
    patronymic text,
    tree_id bigint,
    gender "char" NOT NULL,
    birth_date date,
    date_of_death date,
    CONSTRAINT person_pkey PRIMARY KEY (id),
    CONSTRAINT tree_id FOREIGN KEY (tree_id)
        REFERENCES tree (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON COLUMN person.id
    IS 'Unique identifier';

COMMENT ON COLUMN person.first_name
    IS 'First name of person';

COMMENT ON COLUMN person.last_name
    IS 'Last name of person';

COMMENT ON COLUMN person.patronymic
    IS 'Patronymic of person';

COMMENT ON COLUMN person.tree_id
    IS 'Tree identifier';

COMMENT ON COLUMN person.gender
    IS 'Gender of person';

COMMENT ON COLUMN person.birth_date
    IS 'Birth date';

COMMENT ON COLUMN person.date_of_death
    IS 'Date of death';
