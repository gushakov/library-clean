CREATE TABLE public.works_catalog
(
    isbn   varchar NOT NULL,
    title  varchar NOT NULL,
    author varchar NOT NULL,
    copies integer NOT NULL,
    CONSTRAINT work_pk PRIMARY KEY (isbn)
);

INSERT INTO public.works_catalog (isbn, title, author, copies)
VALUES ('0134494164', 'Clean Architecture', 'Robert Martin', 3),
       ('0321125215', 'Domain-Driven Design', 'Eric Evans', 2);
