CREATE TABLE book(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    genre TEXT NOT NULL,
    author TEXT NOT NULL,
    ISBN TEXT NOT NULL
);