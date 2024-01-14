CREATE TABLE employee (
    employee_id INT PRIMARY KEY,
    email_address VARCHAR(255) UNIQUE NOT NULL,
    employee_password VARCHAR(255) NOT NULL,
    employee_status boolean NOT NULL,
    employee_mobile VARCHAR(255),
    employee_name VARCHAR(255),
    employee_role VARCHAR(255)
);
CREATE TABLE dish (
    dish_id INT PRIMARY KEY,
    dish_name VARCHAR(255) NOT NULL,
    dish_imagepath VARCHAR(255),
    dish_price FLOAT NOT NULL,
    dish_status boolean NOT NULL
);

CREATE TABLE billing (
    bill_id INT PRIMARY KEY,
    user_id INT,
    billing_date DATE NOT NULL,
    billing_time time not null,
    total_amount DECIMAL(10, 2) NOT NULL,
    bill_status boolean default( true)
);

CREATE TABLE billing_details (
    billing_detail_id INT PRIMARY KEY,
    bill_id INT,
    dish_id INT,
    quantity INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
	billing_detail__status boolean default(true)
);



