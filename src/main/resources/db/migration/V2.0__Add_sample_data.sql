-- catalog entries

INSERT INTO public."catalog"
(isbn, title, author)
VALUES ('0134494164', 'Clean Architecture', 'Robert Martin');

INSERT INTO public."catalog"
(isbn, title, author)
VALUES ('0321125215', 'Domain-Driven Design', 'Eric Evans');

INSERT INTO public."catalog"
(isbn, title, author)
VALUES ('0134757599', 'Refactoring', 'Martin Fowler');

-- patrons and holds

INSERT INTO public.patron
(patron_id, full_name, "level")
VALUES ('hLARqY', 'George Clooney', 'REGULAR');

INSERT INTO public."hold"
(isbn, patron_id, start_date, duration, date_completed, date_canceled)
VALUES ('0134494164', 'hLARqY', '2023-05-30', NULL, NULL, NULL);
