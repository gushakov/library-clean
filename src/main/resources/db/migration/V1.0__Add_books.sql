CREATE TABLE public.book
(
    isbn      varchar(10) NOT NULL,
    title     varchar NOT NULL,
    author    varchar NOT NULL,
    "version" int NULL,
    CONSTRAINT book_pk PRIMARY KEY (isbn)
);

INSERT INTO public.book (isbn, title, author)
VALUES ('0134494164', 'Clean Architecture', 'Robert Martin'),
       ('0321125215', 'Domain-Driven Design', 'Eric Evans');
