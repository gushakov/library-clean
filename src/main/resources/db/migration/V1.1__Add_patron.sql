CREATE TABLE public.patron
(
    patron_id varchar NOT NULL,
    full_name varchar NOT NULL,
    "level"   varchar NULL,
    "version" int NULL,
    CONSTRAINT patron_pk PRIMARY KEY (patron_id)
);

CREATE TABLE public."hold"
(
    isbn           varchar NOT NULL,
    patron_id      varchar NOT NULL,
    start_date     date    NOT NULL,
    duration       int NULL,
    date_completed date NULL,
    date_canceled  date NULL,
    CONSTRAINT hold_pk PRIMARY KEY (isbn, patron_id),
    CONSTRAINT hold_catalog_fk FOREIGN KEY (isbn) REFERENCES public."catalog" (isbn),
    CONSTRAINT hold_patron_fk FOREIGN KEY (patron_id) REFERENCES public.patron (patron_id)
);
