CREATE TABLE users (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE properties (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    latitude DECIMAL(10, 7),
    longitude DECIMAL(10, 7),
    unit_number INTEGER NOT NULL
);

CREATE TABLE inspection_processes (
    id UUID PRIMARY KEY,
    properties_id UUID NOT NULL REFERENCES properties(id) ON DELETE CASCADE,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE default_inspection_items (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE inspection_items (
    id UUID PRIMARY KEY,
    process_id UUID NOT NULL REFERENCES inspection_processes(id) ON DELETE CASCADE,
    default_inspection_items UUID NOT NULL REFERENCES default_inspection_items(id) ON DELETE CASCADE,
    additional_item_name VARCHAR(255),
    details TEXT
);
