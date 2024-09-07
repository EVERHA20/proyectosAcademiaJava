create database if not exists library;

use library;

drop table if exists books;

CREATE TABLE `books` (
   `id` int NOT NULL AUTO_INCREMENT,
   `title` varchar(255) NOT NULL,
   `author` varchar(255) NOT NULL,
   `genre` varchar(100) DEFAULT NULL,
   `pages` int DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Autoincrement in id, in case want to run program more than once drop and create table again
INSERT INTO books (title, author, genre, pages) VALUES
('The Silent Patient', 'Alex Michaelides', 'Thriller', 336),
('Where the Crawdads Sing', 'Delia Owens', 'Mystery', 384),
('Educated', 'Tara Westover', 'Memoir', 334),
('Normal People', 'Sally Rooney', 'Romance', 273),
('The Midnight Library', 'Matt Haig', 'Fantasy', 304),
('The Vanishing Half', 'Brit Bennett', 'Historical Fiction', 343),
('Little Fires Everywhere', 'Celeste Ng', 'Drama', 338),
('The Goldfinch', 'Donna Tartt', 'Literary Fiction', 771),
('Circe', 'Madeline Miller', 'Fantasy', 393),
('The Giver of Stars', 'Jojo Moyes', 'Historical Fiction', 400);
