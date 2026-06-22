CREATE DATABASE loom_and_luxe;

USE loom_and_luxe;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255) NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    pincode VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NULL
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    brand VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id) REFERENCES categories(category_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS product_variants (
    variant_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    size VARCHAR(10) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    CONSTRAINT fk_variants_product
        FOREIGN KEY (product_id) REFERENCES products(product_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT uq_product_size UNIQUE (product_id, size)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cart_items (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_items_cart
        FOREIGN KEY (cart_id) REFERENCES cart(cart_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_cart_items_variant
        FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT uq_cart_variant UNIQUE (cart_id, variant_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    order_status VARCHAR(30) NOT NULL DEFAULT 'PLACED',
    payment_method VARCHAR(30) NOT NULL,
    delivery_name VARCHAR(100) NOT NULL,
    delivery_phone VARCHAR(15) NOT NULL,
    delivery_address_line1 VARCHAR(255) NOT NULL,
    delivery_address_line2 VARCHAR(255) NULL,
    delivery_city VARCHAR(100) NOT NULL,
    delivery_state VARCHAR(100) NOT NULL,
    delivery_country VARCHAR(100) NOT NULL,
    delivery_pincode VARCHAR(10) NOT NULL,
    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id) REFERENCES orders(order_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_order_items_variant
        FOREIGN KEY (variant_id) REFERENCES product_variants(variant_id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;







USE loom_and_luxe;

-- =========================================
-- INSERT CATEGORIES
-- =========================================

INSERT INTO categories
(category_name, description)
VALUES

('Men', 'Fashion products for men'),
('Women', 'Fashion products for women'),
('Footwear', 'Shoes, sneakers and sandals'),
('Accessories', 'Fashion accessories and essentials');



-- =========================================
-- INSERT PRODUCTS
-- =========================================

ALTER TABLE products
DROP COLUMN color;

INSERT INTO products
(product_name, description, price, image_url, category_id, brand, is_active)
VALUES

(
'Classic Black T-Shirt',
'Premium cotton black t-shirt',
799.00,
'images/products/black-tshirt.jpg',
1,
'UrbanWear',
1
),

(
'Oversized Hoodie',
'Comfort fit oversized hoodie',
1899.00,
'images/products/hoodie.jpg',
1,
'StreetMode',
1
),

(
'Floral Summer Dress',
'Lightweight floral dress',
2499.00,
'images/products/floral-dress.jpg',
2,
'LuxeStyle',
1
),

(
'Blue Denim Jeans',
'Slim fit denim jeans',
1999.00,
'images/products/jeans.jpg',
2,
'DenimCo',
1
),

(
'White Sneakers',
'Casual white sneakers',
2999.00,
'images/products/sneakers.jpg',
3,
'SoleCraft',
1
),

(
'Leather Sandals',
'Brown leather sandals',
1599.00,
'images/products/sandals.jpg',
3,
'WalkEase',
1
),

(
'Classic Wrist Watch',
'Minimal analog watch',
3499.00,
'images/products/watch.jpg',
4,
'TimeLux',
1
),

(
'Black Sunglasses',
'UV protected sunglasses',
999.00,
'images/products/sunglasses.jpg',
4,
'VisionWear',
1
);


-- =========================================
-- INSERT PRODUCT VARIANTS
-- =========================================

INSERT INTO product_variants
(product_id, size, stock_quantity)
VALUES

-- Black T-Shirt
(1, 'S', 10),
(1, 'M', 15),
(1, 'L', 12),
(1, 'XL', 8),

-- Beige Hoodie
(2, 'M', 10),
(2, 'L', 10),
(2, 'XL', 6),

-- Floral Dress
(3, 'S', 7),
(3, 'M', 9),
(3, 'L', 5),

-- High Waist Jeans
(4, '28', 6),
(4, '30', 10),
(4, '32', 8),
(4, '34', 5),

-- White Sneakers
(5, '7', 10),
(5, '8', 12),
(5, '9', 9),
(5, '10', 6),

-- Leather Sandals
(6, '7', 8),
(6, '8', 10),
(6, '9', 7),

-- Watch
(7, 'FREE', 20),

-- Sunglasses
(8, 'FREE', 15);



-- =========================================
-- INSERT USERS
-- =========================================

INSERT INTO users
(
full_name,
email,
phone,
password,
address_line1,
address_line2,
city,
state,
country,
pincode
)
VALUES

(
'Rahul Sharma',
'rahul@example.com',
'9876543210',
'rahul123',
'12 MG Road',
'Near Metro Station',
'Bangalore',
'Karnataka',
'India',
'560001'
),

(
'Priya Menon',
'priya@example.com',
'9876501234',
'priya123',
'45 Park Street',
'2nd Floor',
'Chennai',
'Tamil Nadu',
'India',
'600001'
),

(
'Arjun Patel',
'arjun@example.com',
'9876512345',
'arjun123',
'78 Ring Road',
'Apartment 5B',
'Hyderabad',
'Telangana',
'India',
'500001'
),

(
'Neha Kapoor',
'neha@example.com',
'9876523456',
'neha123',
'21 Lake View',
'Near Mall',
'Mumbai',
'Maharashtra',
'India',
'400001'
);


-- =========================================
-- INSERT CART
-- =========================================

INSERT INTO cart (user_id)
VALUES
(1),
(2);


-- =========================================
-- INSERT CART ITEMS
-- =========================================

INSERT INTO cart_items
(cart_id, variant_id, quantity)
VALUES

(1, 2, 2),
(1, 5, 1),
(2, 9, 1),
(2, 15, 2);



-- =========================================
-- INSERT ORDERS
-- =========================================

INSERT INTO orders
(
user_id,
total_amount,
order_status,
payment_method,
delivery_name,
delivery_phone,
delivery_address_line1,
delivery_address_line2,
delivery_city,
delivery_state,
delivery_country,
delivery_pincode
)
VALUES

(
1,
3497.00,
'PLACED',
'COD',
'Rahul Sharma',
'9876543210',
'12 MG Road',
'Near Metro Station',
'Bangalore',
'Karnataka',
'India',
'560001'
),

(
2,
2499.00,
'DELIVERED',
'UPI',
'Priya Menon',
'9876501234',
'45 Park Street',
'2nd Floor',
'Chennai',
'Tamil Nadu',
'India',
'600001'
),

(
3,
1899.00,
'SHIPPED',
'CARD',
'Arjun Patel',
'9876512345',
'78 Ring Road',
'Apartment 5B',
'Hyderabad',
'Telangana',
'India',
'500001'
),

(
4,
2999.00,
'PLACED',
'NET_BANKING',
'Neha Kapoor',
'9876523456',
'21 Lake View',
'Near Mall',
'Mumbai',
'Maharashtra',
'India',
'400001'
);


-- =========================================
-- INSERT ORDER ITEMS
-- =========================================

INSERT INTO order_items
(
order_id,
variant_id,
quantity,
price
)
VALUES

(
1,
2,
2,
799.00
),

(
1,
5,
1,
1899.00
),

(
2,
9,
1,
2499.00
),

(
3,
6,
1,
1899.00
),

(
4,
15,
1,
2999.00
);


UPDATE products
SET image_url='assets/images/products/mens-black-tshirt.jpg'
WHERE product_id=1;

UPDATE products
SET image_url='assets/images/products/oversized-hoodie.jpg'
WHERE product_id=2;

UPDATE products
SET image_url='assets/images/products/floral-summer-dress.jpg'
WHERE product_id=3;

UPDATE products
SET image_url='assets/images/products/blue-denim-jeans.jpg'
WHERE product_id=4;

UPDATE products
SET image_url='assets/images/products/white-sneakers.jpg'
WHERE product_id=5;

UPDATE products
SET image_url='assets/images/products/leather-sandals.jpg'
WHERE product_id=6;

UPDATE products
SET image_url='assets/images/products/classic-watch.jpg'
WHERE product_id=7;

UPDATE products
SET image_url='assets/images/products/black-sunglasses.jpg'
WHERE product_id=8;
