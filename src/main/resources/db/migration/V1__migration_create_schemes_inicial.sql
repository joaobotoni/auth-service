CREATE TABLE users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE apartments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    building_name VARCHAR(255) NOT NULL,
    location TEXT NOT NULL,
    unit_number TEXT NOT NULL
);

CREATE TABLE inspection_processes (
    id UUID PRIMARY KEY,
    apartment_id UUID NOT NULL REFERENCES apartments(id) ON DELETE CASCADE,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inspection_items (
    id UUID PRIMARY KEY,
    process_id UUID NOT NULL REFERENCES inspection_processes(id) ON DELETE CASCADE,
    item_name VARCHAR(255) NOT NULL,
    details TEXT
);

CREATE TABLE default_inspection_items (
    id UUID PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL
);
