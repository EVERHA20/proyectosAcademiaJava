-- Drop user first if they exist
DROP USER if exists 'reader'@'%' ;

-- Now create user with prop privileges
CREATE USER 'reader'@'%' IDENTIFIED BY 'reader';

GRANT ALL PRIVILEGES ON * . * TO 'reader'@'%';