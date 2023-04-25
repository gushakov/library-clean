CREATE TABLE public.book_instance
(
    id     varchar(6)  NOT NULL,
    isbn   varchar(10) NOT NULL,
    "type" varchar     NOT NULL,
    CONSTRAINT instance_pk PRIMARY KEY (id),
    CONSTRAINT instance_fk FOREIGN KEY (isbn) REFERENCES public.book (isbn)
);

INSERT INTO public.book_instance
    (id, isbn, "type")
VALUES
    ('ZuWLBP', '0134494164', 'RESTRICTED'),
    ('ejVnPM', '0134494164', 'CIRCULATING');
