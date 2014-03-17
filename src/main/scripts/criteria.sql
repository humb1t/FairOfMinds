CREATE TABLE public.criteria (
  id bigint PRIMARY KEY NOT NULL,
  title character varying(255),
  weight double precision NOT NULL
);
INSERT INTO public.criteria (id, title, weight) VALUES (10, '1. Масштаб достижения', 0.2422519539);
INSERT INTO public.criteria (id, title, weight) VALUES (15, '2. Контекст достижения', 0.0236413699);
INSERT INTO public.criteria (id, title, weight) VALUES (20, '3. Ваша роль', 0.1141559363);
INSERT INTO public.criteria (id, title, weight) VALUES (25, '4. Результаты достижения', 0.1141559363);
INSERT INTO public.criteria (id, title, weight) VALUES (30, '5. Время затраченное на достижение', 0.0510520867);
INSERT INTO public.criteria (id, title, weight) VALUES (35, '6. Конкуренция в данной области', 0.2422519539);
INSERT INTO public.criteria (id, title, weight) VALUES (40, '7. Ваша мотивация', 0.1141559363);
INSERT INTO public.criteria (id, title, weight) VALUES (45, '8. Ваша общая занятость', 0.0236413699);
INSERT INTO public.criteria (id, title, weight) VALUES (50, '9. Ваше материальное состояние', 0.0236413699);
INSERT INTO public.criteria (id, title, weight) VALUES (55, '10. Ваше отношение к сфере достижения', 0.0510520867);