CREATE TABLE public.catalog_entry
(
    isbn      varchar(10) NOT NULL,
    title     varchar     NOT NULL,
    author    varchar     NOT NULL,
    "version" int NULL,
    CONSTRAINT catalog_entry_pk PRIMARY KEY (isbn)
);

INSERT INTO public.catalog_entry (isbn, title, author, version)
VALUES ('0134494164', 'Clean Architecture', 'Robert Martin', 1),
       ('0321125215', 'Domain-Driven Design', 'Eric Evans', 1);
