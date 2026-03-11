USE uniqlo_education;

-- ==========================================================
-- 1. INSERT USERS (5 users)
-- ==========================================================
INSERT INTO users (full_name, email, password_hash, birthday, gender) VALUES
('Nguyen Van A', 'nguyenvana@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1990-05-15', 'Male'),
('Tran Thi B', 'tranthib@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1995-08-20', 'Female'),
('Le Van C', 'levanc@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1988-03-10', 'Male'),
('Pham Thi D', 'phamthid@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1992-11-25', 'Female'),
('Hoang Van E', 'hoangvane@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1985-07-30', 'Male');

-- ==========================================================
-- 2. INSERT COLORS (10 colors)
-- ==========================================================
INSERT INTO colors (color_code, hex_code, created_by) VALUES
('09 BLACK', '#000000', 1),
('00 WHITE', '#FFFFFF', 1),
('69 NAVY', '#001F3F', 1),
('03 GRAY', '#808080', 1),
('12 PINK', '#FFC0CB', 1),
('32 BEIGE', '#F5F5DC', 1),
('56 OLIVE', '#808000', 1),
('66 BLUE', '#0074D9', 1),
('18 RED', '#FF4136', 1),
('54 GREEN', '#2ECC40', 1);

-- ==========================================================
-- 3. INSERT SIZES (10 sizes)
-- ==========================================================
INSERT INTO sizes (size_code, created_by) VALUES
('XXS', 1),
('XS', 1),
('S', 1),
('M', 1),
('L', 1),
('XL', 1),
('XXL', 1),
('3XL', 1),
('4XL', 1),
('5XL', 1);

-- ==========================================================
-- 4. INSERT CATEGORIES (3 parent + 30 child)
-- ==========================================================
-- Parent Categories
INSERT INTO categories (id, name, parent_id, created_by) VALUES
(1, 'Men', NULL, 1),
(2, 'Women', NULL, 1),
(3, 'Kids', NULL, 1);

-- Child Categories for Men (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
('Men T-Shirts', 1, 1),
('Men Shirts', 1, 1),
('Men Pants', 1, 1),
('Men Jeans', 1, 1),
('Men Jackets', 1, 1),
('Men Sweaters', 1, 1),
('Men Innerwear', 1, 1),
('Men Sportswear', 1, 1),
('Men Loungewear', 1, 1),
('Men Accessories', 1, 1);

-- Child Categories for Women (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
('Women T-Shirts', 2, 1),
('Women Blouses', 2, 1),
('Women Pants', 2, 1),
('Women Skirts', 2, 1),
('Women Dresses', 2, 1),
('Women Jackets', 2, 1),
('Women Innerwear', 2, 1),
('Women Sportswear', 2, 1),
('Women Loungewear', 2, 1),
('Women Accessories', 2, 1);

-- Child Categories for Kids (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
('Kids T-Shirts', 3, 1),
('Kids Shirts', 3, 1),
('Kids Pants', 3, 1),
('Kids Dresses', 3, 1),
('Kids Jackets', 3, 1),
('Kids Innerwear', 3, 1),
('Kids Sportswear', 3, 1),
('Kids Sleepwear', 3, 1),
('Kids Accessories', 3, 1),
('Kids Shoes', 3, 1);

-- ==========================================================
-- 5. INSERT PRODUCTS (15 products)
-- ==========================================================
INSERT INTO products (id, category_id, name, description, material_info, created_by) VALUES
(482557, 4, 'Men AIRism Cotton Crew Neck T-Shirt', 'Soft and breathable AIRism cotton blend for maximum comfort', 'AIRism, 100% Cotton', 1),
(482558, 5, 'Men Ultra Light Down Jacket', 'Lightweight and warm down jacket perfect for all seasons', 'Down Fill, Nylon Shell', 1),
(482559, 6, 'Men Slim Fit Jeans', 'Classic slim fit jeans with stretch comfort', '98% Cotton, 2% Spandex', 1),
(482560, 7, 'Men Extra Fine Merino Crew Neck Sweater', 'Premium merino wool sweater', '100% Merino Wool', 1),
(482561, 8, 'Men Supima Cotton Oxford Shirt', 'Classic oxford shirt in premium Supima cotton', '100% Supima Cotton', 1),

(482562, 14, 'Women AIRism UV Cut Long Sleeve T-Shirt', 'UV protection with AIRism technology', 'AIRism, Polyester Blend', 1),
(482563, 18, 'Women Ultra Stretch High Rise Jeans', 'High rise jeans with ultimate stretch', '95% Cotton, 5% Spandex', 1),
(482564, 19, 'Women Rayon Long Skirt', 'Elegant flowing skirt for any occasion', '100% Rayon', 1),
(482565, 20, 'Women Soft Touch Dress', 'Comfortable and stylish dress', 'Cotton Blend', 1),
(482566, 21, 'Women Pocketable UV Protection Jacket', 'Lightweight packable jacket with UV protection', 'Polyester', 1),

(482567, 24, 'Kids Cotton Graphic T-Shirt', 'Fun graphic tees for kids', '100% Cotton', 1),
(482568, 27, 'Kids Stretch Denim Pants', 'Comfortable stretch denim for active kids', '98% Cotton, 2% Spandex', 1),
(482569, 28, 'Kids Pufftech Jacket', 'Warm and lightweight jacket for kids', 'Synthetic Fill, Nylon', 1),
(482570, 29, 'Kids HEATTECH Innerwear Set', 'Thermal innerwear for cold weather', 'HEATTECH Technology', 1),
(482571, 30, 'Kids Ultra Stretch Jogger Pants', 'Comfortable joggers for everyday wear', 'Cotton Blend with Stretch', 1);

-- ==========================================================
-- 6. INSERT PRODUCT SKUS (45 SKUs - 3 per product)
-- ==========================================================
-- Product 482557 (Men T-Shirt) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482557, 1, 4, '482557-09BLACK-M', 290000, 232000, 100, 1),
(482557, 3, 5, '482557-69NAVY-L', 290000, 232000, 150, 1),
(482557, 2, 3, '482557-00WHITE-S', 290000, NULL, 80, 1);

-- Product 482558 (Men Down Jacket) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482558, 1, 5, '482558-09BLACK-L', 1290000, 990000, 50, 1),
(482558, 3, 4, '482558-69NAVY-M', 1290000, 990000, 60, 1),
(482558, 4, 6, '482558-03GRAY-XL', 1290000, NULL, 40, 1);

-- Product 482559 (Men Jeans) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482559, 3, 4, '482559-69NAVY-M', 790000, 632000, 120, 1),
(482559, 1, 5, '482559-09BLACK-L', 790000, 632000, 100, 1),
(482559, 4, 3, '482559-03GRAY-S', 790000, NULL, 90, 1);

-- Product 482560 (Men Sweater) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482560, 3, 4, '482560-69NAVY-M', 590000, NULL, 80, 1),
(482560, 4, 5, '482560-03GRAY-L', 590000, NULL, 70, 1),
(482560, 1, 4, '482560-09BLACK-M', 590000, 472000, 85, 1);

-- Product 482561 (Men Oxford Shirt) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482561, 2, 4, '482561-00WHITE-M', 490000, NULL, 100, 1),
(482561, 3, 5, '482561-69NAVY-L', 490000, NULL, 95, 1),
(482561, 8, 4, '482561-66BLUE-M', 490000, 392000, 90, 1);

-- Product 482562 (Women T-Shirt) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482562, 2, 3, '482562-00WHITE-S', 390000, 312000, 110, 1),
(482562, 1, 4, '482562-09BLACK-M', 390000, 312000, 100, 1),
(482562, 5, 3, '482562-12PINK-S', 390000, NULL, 95, 1);

-- Product 482563 (Women Jeans) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482563, 3, 3, '482563-69NAVY-S', 890000, 712000, 120, 1),
(482563, 1, 4, '482563-09BLACK-M', 890000, 712000, 130, 1),
(482563, 4, 5, '482563-03GRAY-L', 890000, NULL, 80, 1);

-- Product 482564 (Women Skirt) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482564, 1, 3, '482564-09BLACK-S', 690000, NULL, 75, 1),
(482564, 6, 4, '482564-32BEIGE-M', 690000, 552000, 85, 1),
(482564, 3, 4, '482564-69NAVY-M', 690000, NULL, 90, 1);

-- Product 482565 (Women Dress) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482565, 1, 3, '482565-09BLACK-S', 990000, 792000, 60, 1),
(482565, 5, 4, '482565-12PINK-M', 990000, 792000, 70, 1),
(482565, 2, 5, '482565-00WHITE-L', 990000, NULL, 55, 1);

-- Product 482566 (Women Jacket) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482566, 6, 3, '482566-32BEIGE-S', 1190000, 952000, 50, 1),
(482566, 3, 4, '482566-69NAVY-M', 1190000, 952000, 55, 1),
(482566, 1, 4, '482566-09BLACK-M', 1190000, NULL, 45, 1);

-- Product 482567 (Kids T-Shirt) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482567, 9, 2, '482567-18RED-XS', 190000, 152000, 100, 1),
(482567, 8, 3, '482567-66BLUE-S', 190000, 152000, 110, 1),
(482567, 10, 2, '482567-54GREEN-XS', 190000, NULL, 95, 1);

-- Product 482568 (Kids Pants) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482568, 3, 3, '482568-69NAVY-S', 390000, NULL, 85, 1),
(482568, 1, 4, '482568-09BLACK-M', 390000, 312000, 90, 1),
(482568, 4, 3, '482568-03GRAY-S', 390000, NULL, 80, 1);

-- Product 482569 (Kids Jacket) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482569, 9, 3, '482569-18RED-S', 790000, 632000, 70, 1),
(482569, 3, 4, '482569-69NAVY-M', 790000, 632000, 75, 1),
(482569, 1, 3, '482569-09BLACK-S', 790000, NULL, 65, 1);

-- Product 482570 (Kids Innerwear) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482570, 1, 2, '482570-09BLACK-XS', 290000, NULL, 100, 1),
(482570, 4, 3, '482570-03GRAY-S', 290000, 232000, 95, 1),
(482570, 3, 2, '482570-69NAVY-XS', 290000, NULL, 90, 1);

-- Product 482571 (Kids Joggers) - 3 variants
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
(482571, 4, 3, '482571-03GRAY-S', 490000, 392000, 85, 1),
(482571, 1, 4, '482571-09BLACK-M', 490000, 392000, 90, 1),
(482571, 3, 3, '482571-69NAVY-S', 490000, NULL, 80, 1);

-- ==========================================================
-- 7. INSERT PRODUCT IMAGES (3 images per product-color combo)
-- ==========================================================
-- Product 482557 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482557, 1, '/images/482557_black_1.jpg', TRUE, 1),
(482557, 1, '/images/482557_black_2.jpg', FALSE, 2),
(482557, 3, '/images/482557_navy_1.jpg', TRUE, 1),
(482557, 3, '/images/482557_navy_2.jpg', FALSE, 2),
(482557, 2, '/images/482557_white_1.jpg', TRUE, 1);

-- Product 482558 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482558, 1, '/images/482558_black_1.jpg', TRUE, 1),
(482558, 3, '/images/482558_navy_1.jpg', TRUE, 1),
(482558, 4, '/images/482558_gray_1.jpg', TRUE, 1);

-- Product 482559 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482559, 3, '/images/482559_navy_1.jpg', TRUE, 1),
(482559, 1, '/images/482559_black_1.jpg', TRUE, 1),
(482559, 4, '/images/482559_gray_1.jpg', TRUE, 1);

-- Product 482560 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482560, 3, '/images/482560_navy_1.jpg', TRUE, 1),
(482560, 4, '/images/482560_gray_1.jpg', TRUE, 1),
(482560, 1, '/images/482560_black_1.jpg', TRUE, 1);

-- Product 482561 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482561, 2, '/images/482561_white_1.jpg', TRUE, 1),
(482561, 3, '/images/482561_navy_1.jpg', TRUE, 1),
(482561, 8, '/images/482561_blue_1.jpg', TRUE, 1);

-- Product 482562 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482562, 2, '/images/482562_white_1.jpg', TRUE, 1),
(482562, 1, '/images/482562_black_1.jpg', TRUE, 1),
(482562, 5, '/images/482562_pink_1.jpg', TRUE, 1);

-- Product 482563 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482563, 3, '/images/482563_navy_1.jpg', TRUE, 1),
(482563, 1, '/images/482563_black_1.jpg', TRUE, 1),
(482563, 4, '/images/482563_gray_1.jpg', TRUE, 1);

-- Product 482564 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482564, 1, '/images/482564_black_1.jpg', TRUE, 1),
(482564, 6, '/images/482564_beige_1.jpg', TRUE, 1),
(482564, 3, '/images/482564_navy_1.jpg', TRUE, 1);

-- Product 482565 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482565, 1, '/images/482565_black_1.jpg', TRUE, 1),
(482565, 5, '/images/482565_pink_1.jpg', TRUE, 1),
(482565, 2, '/images/482565_white_1.jpg', TRUE, 1);

-- Product 482566 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482566, 6, '/images/482566_beige_1.jpg', TRUE, 1),
(482566, 3, '/images/482566_navy_1.jpg', TRUE, 1),
(482566, 1, '/images/482566_black_1.jpg', TRUE, 1);

-- Product 482567 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482567, 9, '/images/482567_red_1.jpg', TRUE, 1),
(482567, 8, '/images/482567_blue_1.jpg', TRUE, 1),
(482567, 10, '/images/482567_green_1.jpg', TRUE, 1);

-- Product 482568 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482568, 3, '/images/482568_navy_1.jpg', TRUE, 1),
(482568, 1, '/images/482568_black_1.jpg', TRUE, 1),
(482568, 4, '/images/482568_gray_1.jpg', TRUE, 1);

-- Product 482569 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482569, 9, '/images/482569_red_1.jpg', TRUE, 1),
(482569, 3, '/images/482569_navy_1.jpg', TRUE, 1),
(482569, 1, '/images/482569_black_1.jpg', TRUE, 1);

-- Product 482570 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482570, 1, '/images/482570_black_1.jpg', TRUE, 1),
(482570, 4, '/images/482570_gray_1.jpg', TRUE, 1),
(482570, 3, '/images/482570_navy_1.jpg', TRUE, 1);

-- Product 482571 images
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
(482571, 4, '/images/482571_gray_1.jpg', TRUE, 1),
(482571, 1, '/images/482571_black_1.jpg', TRUE, 1),
(482571, 3, '/images/482571_navy_1.jpg', TRUE, 1);

-- ==========================================================
-- 8. INSERT CART ITEMS (Sample data)
-- ==========================================================
INSERT INTO cart_items (user_id, sku_id, quantity) VALUES
(1, 1, 2),
(1, 4, 1),
(2, 7, 1),
(2, 10, 2),
(3, 13, 1),
(4, 19, 3),
(5, 25, 1);

-- ==========================================================
-- 9. INSERT REVIEWS (Sample data)
-- ==========================================================
INSERT INTO reviews (product_id, user_id, sku_id, rating, comment, user_height, user_weight, fit_status) VALUES
(482557, 2, 1, 5, 'Great quality T-shirt, very comfortable!', '170cm', '65kg', 'True to size'),
(482557, 3, 2, 4, 'Good material but color fades after washing', '175cm', '70kg', 'True to size'),
(482558, 1, 4, 5, 'Perfect jacket for winter, very warm!', '180cm', '75kg', 'True to size'),
(482559, 4, 7, 5, 'Best jeans I have ever bought!', '165cm', '55kg', 'Runs small'),
(482560, 5, 10, 4, 'Nice sweater but a bit pricey', '172cm', '68kg', 'True to size'),
(482562, 2, 16, 5, 'Love the UV protection feature!', '160cm', '50kg', 'True to size'),
(482563, 3, 19, 5, 'Super stretchy and comfortable', '168cm', '58kg', 'True to size'),
(482565, 4, 25, 4, 'Beautiful dress, perfect for summer', '165cm', '52kg', 'Runs large'),
(482567, 1, 34, 5, 'My kid loves this shirt!', '120cm', '25kg', 'True to size'),
(482569, 5, 40, 5, 'Excellent jacket for kids, very durable', '130cm', '30kg', 'True to size');

