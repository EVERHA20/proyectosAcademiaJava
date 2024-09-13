INSERT INTO `user_roles` (user_id, role_id) VALUES
((SELECT id FROM `user` WHERE username = 'user'), (SELECT id FROM `roles` WHERE role_name = 'ROLE_USER')),
((SELECT id FROM `user` WHERE username = 'admin'), (SELECT id FROM `roles` WHERE role_name = 'ROLE_ADMIN')),
((SELECT id FROM `user` WHERE username = 'admin'), (SELECT id FROM `roles` WHERE role_name = 'ROLE_USER'));

INSERT INTO book_genres (book_id, genre_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 4),
(6, 4),
(7, 1),
(8, 5),
(9, 5),
(10, 3);