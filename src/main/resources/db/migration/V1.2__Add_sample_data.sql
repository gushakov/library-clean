--
-- We simply add all the data required to execute hold book use case successfully.
-- The two catalog entries and the patron.
--

INSERT INTO public."catalog"
    (isbn, title, author, "version")
VALUES ('0134494164', 'Clean Architecture', 'Robert C. Martin', 1);

INSERT INTO public."catalog"
    (isbn, title, author, "version")
VALUES ('0321125215', 'Domain-Driven Design', 'Eric Evans', 1);

INSERT INTO public.patron
    (patron_id, full_name, "level", "version")
VALUES ('patron1', 'Brad Pitt', 'REGULAR', 1);
