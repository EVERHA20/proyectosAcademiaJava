INSERT INTO `user` (username, password, enabled) VALUES
('user', 'user', b'1'),
('admin', 'admin', b'1');

INSERT INTO `roles` (role_name) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO genres (name) VALUES
('Science Fiction'),
('Fantasy'),
('Mystery'),
('Romance'),
('Historical Fiction');

INSERT INTO books (title, author, price, pages) VALUES
('Dune', 'Frank Herbert', 9.99, 412),
('The Hobbit', 'J.R.R. Tolkien', 12.99, 310),
('The Da Vinci Code', 'Dan Brown', 14.99, 489),
('Pride and Prejudice', 'Jane Austen', 7.99, 279),
('The Catcher in the Rye', 'J.D. Salinger', 8.99, 214),
('To Kill a Mockingbird', 'Harper Lee', 10.99, 281),
('1984', 'George Orwell', 6.99, 328),
('The Great Gatsby', 'F. Scott Fitzgerald', 11.99, 180),
('War and Peace', 'Leo Tolstoy', 13.99, 1225),
('The Girl with the Dragon Tattoo', 'Stieg Larsson', 15.99, 465);