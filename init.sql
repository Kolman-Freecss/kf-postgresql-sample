-- Create the 'users' table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

-- Create the 'orders' table to demonstrate INNER JOIN
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    product VARCHAR(100),
    amount DECIMAL
);

-- Insert some sample data
INSERT INTO users (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Doe', 'jane.doe@example.com');

INSERT INTO orders (user_id, product, amount) VALUES
(1, 'Laptop', 1000.00),
(2, 'Phone', 500.00);
