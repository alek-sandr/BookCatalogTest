CREATE DATABASE IF NOT EXISTS bookcatalog;
CREATE USER bookcatalog@localhost identified BY 'secret';
CREATE USER bookcatalog@127.0.0.1 identified BY 'secret';
GRANT ALL privileges ON bookcatalog.* TO bookcatalog@localhost;
GRANT ALL privileges ON bookcatalog.* TO bookcatalog@127.0.0.1;
