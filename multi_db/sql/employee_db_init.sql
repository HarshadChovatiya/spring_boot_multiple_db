CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    email TEXT,
    company_id INTEGER
    
);

INSERT INTO "employee" (first_name, last_name, email, company_id) VALUES
('Mike', 'Doe', 'mike.doe@yopmail.com', 1),
('John', 'Yang', 'john.yang@yopmail.com', 2);