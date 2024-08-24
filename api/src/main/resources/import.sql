------------------Roles-------------
INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 1, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'usuario(cliente)', 'ROLE_USER');

INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 2, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'administrador de la clinica', 'ROLE_ADMIN');

INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 3, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'especialistas', 'ROLE_SPECIALIST');

------------------User--------------------
INSERT INTO users(active, created_at, deleted_at, updated_at, create_by, delete_by, update_by, user_id, password, username) VALUES (true, '2024-08-24', null, null, 'd0b4c3d8-6beb-40b7-871b-177ee66627cb', null, null, '895769e3-1828-40c6-8b97-257f82853075', '$2y$10$j8O64cfWP70WHySKmYFnbuBwIBP.XuXmlPKiTA9p56vCZUwNz7gnm', 'admin@gmail.com');

-----------------User-role----------------
INSERT INTO user_role(role_id, user_id)VALUES (2, '895769e3-1828-40c6-8b97-257f82853075');

------- Country -------
INSERT INTO countries(country_id, country_name) VALUES ('1', 'Argentina');
INSERT INTO countries(country_id, country_name) VALUES ('2', 'Perú');

------ Department ------
INSERT INTO departments(country_id, department_id, department_name) VALUES (1, 1, 'Provincia de Buenos Aires');
INSERT INTO departments(country_id, department_id, department_name) VALUES (2, 2, 'Lima');

----- City ------
INSERT INTO cities(city_id, department_id, city_name) VALUES (1, 1, 'Buenos Aires');
INSERT INTO cities(city_id, department_id, city_name) VALUES (2, 2, 'Lima Metropolitana');

----- District ----
INSERT INTO districts(city_id, district_id, district_name) VALUES (1, 1, 'La Plata');
INSERT INTO districts(city_id, district_id, district_name) VALUES (2, 2, 'Cercado de Lima')


-----------------Profile-------------------
INSERT INTO profiles(active, birth, created_at, deleted_at, district_id, updated_at, document_number, create_by, delete_by, profile_id, update_by, user_id, profile_name, profile_lastname, address, avatar_url, document_type) VALUES (true, '1975-06-25', '2024-08-24', null, 1, null, '81.544.670','895769e3-1828-40c6-8b97-257f82853075', null,'6097656c-e788-45cb-a41f-73d4e031ee60', null, '895769e3-1828-40c6-8b97-257f82853075', 'Susan', 'Martinez', 'Calle La Plata 259', null, 'DNI');