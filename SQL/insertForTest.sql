INSERT INTO public.project(
	id_project, name, project_start, project_end)
	VALUES (default, 'Babell', 'September 26, 2019', 'October 2, 2019');

INSERT INTO public.project(
	id_project, name, project_start, project_end)
	VALUES (default, 'Test', 'October 26, 2019', 'November 6, 2019');

INSERT INTO public.project(
	id_project, name, project_start, project_end)
	VALUES (default, 'Coucou', 'September 15, 2019', 'October 9, 2019');
	
INSERT INTO public.project(
	id_project, name, project_start, project_end)
	VALUES (default, 'Afelio', 'December 26, 2019', 'January 8, 2020');
	
INSERT INTO public.project(
	id_project, name, project_start, project_end)
	VALUES (default, 'Nrb', 'June 26, 2019', 'December 2, 2019');

INSERT INTO public.todo(
	id_todo, name, description, in_progress, done, id_project)
	VALUES (default, 'testForUpdate3', 'test description', false, false, 2);

INSERT INTO public.todo(
	id_todo, name, description, in_progress, done, id_project)
	VALUES (default, 'test', 'test description', false, false, 2);

INSERT INTO public.person(
	id_person, firstname, lastname, email, password)
	VALUES (default, 'Delphine', 'Franquinet', 'delphine@mail.be', '$2a$10$SyzfxN1G2Hd9yjv7wW4buukAUb9mnmthLJB8MlCyy8gk3QGcHC3lq');

INSERT INTO public.person(
	id_person, firstname, lastname, email, password)
	VALUES (default, 'Meliss', 'Schyns', 'melissa@mail.be', '$2a$10$SyzfxN1G2Hd9yjv7wW4buukAUb9mnmthLJB8MlCyy8gk3QGcHC3lq');

INSERT INTO public.person(
	id_person, firstname, lastname, email, password)
	VALUES (default, 'Damien', 'Bouffioux', 'damien@mail.be', '$2a$10$SyzfxN1G2Hd9yjv7wW4buukAUb9mnmthLJB8MlCyy8gk3QGcHC3lq');

INSERT INTO public.todo_person(
	id_todo_person, id_todo, id_person)
	VALUES (default, 1, 2);
INSERT INTO public.todo_person(
	id_todo_person, id_todo, id_person)
	VALUES (default, 2, 1);

