CREATE TABLE public."catalog"
(
    isbn      varchar NOT NULL,
    title     varchar NOT NULL,
    author    varchar NOT NULL,
    "version" int NULL,
    CONSTRAINT catalog_pk PRIMARY KEY (isbn)
);

INSERT INTO public."catalog"
    (isbn, title, author)
VALUES ('0134494164', 'Clean Architecture', 'Robert Martin');

INSERT INTO public."catalog"
    (isbn, title, author)
VALUES ('0321125215', 'Domain-Driven Design', 'Eric Evans');

INSERT INTO public."catalog"
    (isbn, title, author)
VALUES ('0134757599', 'Refactoring', 'Martin Fowler');


