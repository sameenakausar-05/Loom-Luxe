-- Seed Categories
INSERT INTO categories (category_id, category_name, description) VALUES
(1, 'Shirts', 'Premium cotton shirts for casual and formal wear'),
(2, 'T-Shirts', 'Comfortable everyday t-shirts'),
(3, 'Jeans & Pants', 'Classic denims and formal trousers');

-- Seed Products
INSERT INTO products (product_id, category_id, product_name, description, brand, color, price, image_url, is_active) VALUES
(1, 1, 'Classic White Oxford Shirt', 'Crafted from 100% premium cotton, this classic white oxford shirt features a button-down collar and a tailored fit.', 'Loom & Luxe Signature', 'White', 1499.00, 'https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?auto=format&fit=crop&w=600&q=80', 1),
(2, 1, 'Navy Blue Linen Shirt', 'Lightweight and breathable, this navy blue linen shirt is perfect for warm summer days or casual weekends.', 'Loom & Luxe Signature', 'Navy Blue', 1699.00, 'https://images.unsplash.com/photo-1596755094514-f87e34085b2c?auto=format&fit=crop&w=600&q=80', 1),
(3, 2, 'Supima Cotton Black T-Shirt', 'Made from premium long-staple Supima cotton for ultimate softness, durability, and color retention.', 'Luxe Basic', 'Black', 799.00, 'https://images.unsplash.com/photo-1521572267360-ee0c2909d518?auto=format&fit=crop&w=600&q=80', 1),
(4, 2, 'Heather Grey Crewneck Tee', 'A versatile heather grey crewneck t-shirt with a relaxed fit and incredibly soft feel.', 'Luxe Basic', 'Grey', 799.00, 'https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?auto=format&fit=crop&w=600&q=80', 1),
(5, 3, 'Slim Fit Indigo Jeans', 'Classic indigo dyed slim fit jeans with a hint of stretch for comfort and style.', 'Denim Co.', 'Indigo Blue', 2499.00, 'https://images.unsplash.com/photo-1542272604-787c3835535d?auto=format&fit=crop&w=600&q=80', 1);

-- Seed Product Variants
INSERT INTO product_variants (variant_id, product_id, size, stock_quantity) VALUES
(1, 1, 'S', 15),
(2, 1, 'M', 20),
(3, 1, 'L', 10),
(4, 2, 'M', 12),
(5, 2, 'L', 18),
(6, 3, 'S', 25),
(7, 3, 'M', 30),
(8, 3, 'L', 20),
(9, 4, 'S', 15),
(10, 4, 'M', 25),
(11, 4, 'L', 15),
(12, 5, '30', 10),
(13, 5, '32', 15),
(14, 5, '34', 12);

-- Seed Test User
INSERT INTO users (user_id, full_name, email, phone, password, address_line1, address_line2, city, state, country, pincode) VALUES
(1, 'John Doe', 'test@test.com', '1234567890', 'password', '123 Main St', 'Apt 4B', 'Mumbai', 'Maharashtra', 'India', '400001');

-- Seed Cart for Test User
INSERT INTO cart (cart_id, user_id) VALUES (1, 1);
