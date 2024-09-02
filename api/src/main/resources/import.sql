------------------Roles-------------
INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 1, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'usuario(cliente)', 'ROLE_USER');

INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 2, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'administrador de la clinica', 'ROLE_ADMIN');

INSERT INTO roles(active, created_at, deleted_at, updated_at, role_id, create_by, delete_by, update_by, role_description, role_name) VALUES (true, '2024-08-22 14:00:00', null, null, 3, '177b61e6-51ad-4185-894b-dc7495599cfc', null, null, 'especialistas', 'ROLE_SPECIALIST');