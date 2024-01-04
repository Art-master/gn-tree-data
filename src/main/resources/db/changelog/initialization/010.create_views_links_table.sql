--liquibase formatted sql

--changeset goltsev_GN:create_views_links_table_29.12.2023

CREATE TABLE IF NOT EXISTS views_links
(
    id uuid NOT NULL,
    view_id uuid,
	linked_view_id uuid,
	tree_view_id bigint NOT NULL,
    CONSTRAINT views_links_pkey PRIMARY KEY (id),
    CONSTRAINT tree_view_id FOREIGN KEY (tree_view_id)
        REFERENCES tree_view (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
);

COMMENT ON TABLE views_links
    IS 'Describes links between views';

COMMENT ON COLUMN views_links.id
    IS 'Unique identifier';

COMMENT ON COLUMN views_links.view_id
    IS 'Unique identifier of view';

COMMENT ON COLUMN views_links.linked_view_id
    IS 'Unique identifier of linked view';

COMMENT ON COLUMN views_links.tree_view_id
    IS 'Tree view identifier';