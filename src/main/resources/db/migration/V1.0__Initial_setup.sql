CREATE TABLE public."catalog"
(
    isbn      varchar NOT NULL,
    title     varchar NOT NULL,
    author    varchar NOT NULL,
    "version" int NULL,
    CONSTRAINT catalog_pk PRIMARY KEY (isbn)
);

