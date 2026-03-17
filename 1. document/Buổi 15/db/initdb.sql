USE uniqlo_education;

-- ==========================================================
-- 1. INSERT USERS (5 users)
-- ==========================================================
INSERT INTO users (full_name, email, password_hash, birthday, gender) VALUES
                                                                          ('Nguyễn Văn An', 'nguyenvanan@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1990-05-15', 'Male'),
                                                                          ('Trần Thị Bình', 'tranthib@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1995-08-20', 'Female'),
                                                                          ('Lê Văn Cường', 'levanc@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1988-03-10', 'Male'),
                                                                          ('Phạm Thị Dung', 'phamthid@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1992-11-25', 'Female'),
                                                                          ('Hoàng Văn Đức', 'hoangvane@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', '1985-07-30', 'Male');

-- ==========================================================
-- 2. INSERT COLORS (10 colors)
-- ==========================================================
INSERT INTO colors (color_code, hex_code, created_by) VALUES
                                                          ('09 ĐEN', '#000000', 1),
                                                          ('00 TRẮNG', '#FFFFFF', 1),
                                                          ('69 XANH NAVY', '#001F3F', 1),
                                                          ('03 XÁM', '#808080', 1),
                                                          ('12 HỒNG', '#FFC0CB', 1),
                                                          ('32 BE', '#F5F5DC', 1),
                                                          ('56 Ô LIU', '#808000', 1),
                                                          ('66 XANH DƯƠNG', '#0074D9', 1),
                                                          ('18 ĐỎ', '#FF4136', 1),
                                                          ('54 XANH LÁ', '#2ECC40', 1);

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
                                                             (1, 'Thời Trang Nam', NULL, 1),
                                                             (2, 'Thời Trang Nữ', NULL, 1),
                                                             (3, 'Thời Trang Trẻ Em', NULL, 1);

-- Child Categories for Men (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
                                                         ('Áo Thun Nam', 1, 1),
                                                         ('Áo Sơ Mi Nam', 1, 1),
                                                         ('Quần Tây Nam', 1, 1),
                                                         ('Quần Jean Nam', 1, 1),
                                                         ('Áo Khoác Nam', 1, 1),
                                                         ('Áo Len Nam', 1, 1),
                                                         ('Đồ Lót Nam', 1, 1),
                                                         ('Đồ Thể Thao Nam', 1, 1),
                                                         ('Đồ Mặc Nhà Nam', 1, 1),
                                                         ('Phụ Kiện Nam', 1, 1);

-- Child Categories for Women (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
                                                         ('Áo Thun Nữ', 2, 1),
                                                         ('Áo Kiểu Nữ', 2, 1),
                                                         ('Quần Tây Nữ', 2, 1),
                                                         ('Chân Váy Nữ', 2, 1),
                                                         ('Váy Đầm Nữ', 2, 1),
                                                         ('Áo Khoác Nữ', 2, 1),
                                                         ('Đồ Lót Nữ', 2, 1),
                                                         ('Đồ Thể Thao Nữ', 2, 1),
                                                         ('Đồ Mặc Nhà Nữ', 2, 1),
                                                         ('Phụ Kiện Nữ', 2, 1);

-- Child Categories for Kids (10)
INSERT INTO categories (name, parent_id, created_by) VALUES
                                                         ('Áo Thun Trẻ Em', 3, 1),
                                                         ('Áo Sơ Mi Trẻ Em', 3, 1),
                                                         ('Quần Trẻ Em', 3, 1),
                                                         ('Váy Đầm Trẻ Em', 3, 1),
                                                         ('Áo Khoác Trẻ Em', 3, 1),
                                                         ('Đồ Lót Trẻ Em', 3, 1),
                                                         ('Đồ Thể Thao Trẻ Em', 3, 1),
                                                         ('Đồ Ngủ Trẻ Em', 3, 1),
                                                         ('Phụ Kiện Trẻ Em', 3, 1),
                                                         ('Giày Dép Trẻ Em', 3, 1);

-- ==========================================================
-- 5. INSERT PRODUCTS (15 products)
-- ==========================================================
INSERT INTO products (id, category_id, name, description, material_info, created_by) VALUES
                                                                                         (482557, 4, 'Áo Thun Nam Cổ Tròn AIRism', 'Áo thun mềm mại và thoáng khí với công nghệ AIRism', 'AIRism, 100% Cotton', 1),
                                                                                         (482558, 5, 'Áo Phao Lông Vũ Siêu Nhẹ Nam', 'Áo phao nhẹ và ấm áp hoàn hảo cho mọi mùa', 'Lông vũ, Vỏ Nylon', 1),
                                                                                         (482559, 6, 'Quần Jean Nam Dáng Ôm', 'Quần jean dáng ôm cổ điển với độ co giãn thoải mái', '98% Cotton, 2% Spandex', 1),
                                                                                         (482560, 7, 'Áo Len Cổ Tròn Merino Cao Cấp Nam', 'Áo len lông cừu merino cao cấp', '100% Lông Cừu Merino', 1),
                                                                                         (482561, 8, 'Áo Sơ Mi Oxford Cotton Supima Nam', 'Áo sơ mi oxford cổ điển bằng cotton Supima cao cấp', '100% Cotton Supima', 1),

                                                                                         (482562, 14, 'Áo Thun Dài Tay Chống UV AIRism Nữ', 'Chống tia UV với công nghệ AIRism', 'AIRism, Polyester pha', 1),
                                                                                         (482563, 18, 'Quần Jean Nữ Co Giãn Cực Đại Lưng Cao', 'Quần jean lưng cao với độ co giãn tối ưu', '95% Cotton, 5% Spandex', 1),
                                                                                         (482564, 19, 'Chân Váy Dài Rayon Nữ', 'Chân váy thanh lịch cho mọi dịp', '100% Rayon', 1),
                                                                                         (482565, 20, 'Váy Đầm Mềm Mại Nữ', 'Váy đầm thoải mái và phong cách', 'Cotton pha', 1),
                                                                                         (482566, 21, 'Áo Khoác Chống UV Gấp Gọn Nữ', 'Áo khoác nhẹ có thể gấp gọn với khả năng chống UV', 'Polyester', 1),

                                                                                         (482567, 24, 'Áo Thun Họa Tiết Cotton Trẻ Em', 'Áo thun họa tiết vui nhộn cho trẻ em', '100% Cotton', 1),
                                                                                         (482568, 27, 'Quần Jean Co Giãn Trẻ Em', 'Quần jean co giãn thoải mái cho trẻ em năng động', '98% Cotton, 2% Spandex', 1),
                                                                                         (482569, 29, 'Áo Khoác Pufftech Trẻ Em', 'Áo khoác ấm áp và nhẹ nhàng cho trẻ em', 'Lông tổng hợp, Nylon', 1),
                                                                                         (482570, 30, 'Bộ Đồ Lót HEATTECH Trẻ Em', 'Đồ lót giữ nhiệt cho thời tiết lạnh', 'Công nghệ HEATTECH', 1),
                                                                                         (482571, 31, 'Quần Jogger Co Giãn Tối Đa Trẻ Em', 'Quần jogger thoải mái cho hàng ngày', 'Cotton pha co giãn', 1);

-- ==========================================================
-- 6. INSERT PRODUCT SKUS (45 SKUs - 3 per product)
-- ==========================================================
-- Product 482557
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482557, 1, 4, '482557-09ĐEN-M', 290000, 232000, 100, 1),
                                                                                                                               (482557, 3, 5, '482557-69NAVY-L', 290000, 232000, 150, 1),
                                                                                                                               (482557, 2, 3, '482557-00TRẮNG-S', 290000, NULL, 80, 1);

-- Product 482558
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482558, 1, 5, '482558-09ĐEN-L', 1290000, 990000, 50, 1),
                                                                                                                               (482558, 3, 4, '482558-69NAVY-M', 1290000, 990000, 60, 1),
                                                                                                                               (482558, 4, 6, '482558-03XÁM-XL', 1290000, NULL, 40, 1);

-- Product 482559
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482559, 3, 4, '482559-69NAVY-M', 790000, 632000, 120, 1),
                                                                                                                               (482559, 1, 5, '482559-09ĐEN-L', 790000, 632000, 100, 1),
                                                                                                                               (482559, 4, 3, '482559-03XÁM-S', 790000, NULL, 90, 1);

-- Product 482560
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482560, 3, 4, '482560-69NAVY-M', 590000, NULL, 80, 1),
                                                                                                                               (482560, 4, 5, '482560-03XÁM-L', 590000, NULL, 70, 1),
                                                                                                                               (482560, 1, 4, '482560-09ĐEN-M', 590000, 472000, 85, 1);

-- Product 482561
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482561, 2, 4, '482561-00TRẮNG-M', 490000, NULL, 100, 1),
                                                                                                                               (482561, 3, 5, '482561-69NAVY-L', 490000, NULL, 95, 1),
                                                                                                                               (482561, 8, 4, '482561-66XANHDUONG-M', 490000, 392000, 90, 1);

-- Product 482562
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482562, 2, 3, '482562-00TRẮNG-S', 390000, 312000, 110, 1),
                                                                                                                               (482562, 1, 4, '482562-09ĐEN-M', 390000, 312000, 100, 1),
                                                                                                                               (482562, 5, 3, '482562-12HỒNG-S', 390000, NULL, 95, 1);

-- Product 482563
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482563, 3, 3, '482563-69NAVY-S', 890000, 712000, 120, 1),
                                                                                                                               (482563, 1, 4, '482563-09ĐEN-M', 890000, 712000, 130, 1),
                                                                                                                               (482563, 4, 5, '482563-03XÁM-L', 890000, NULL, 80, 1);

-- Product 482564
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482564, 1, 3, '482564-09ĐEN-S', 690000, NULL, 75, 1),
                                                                                                                               (482564, 6, 4, '482564-32BE-M', 690000, 552000, 85, 1),
                                                                                                                               (482564, 3, 4, '482564-69NAVY-M', 690000, NULL, 90, 1);

-- Product 482565
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482565, 1, 3, '482565-09ĐEN-S', 990000, 792000, 60, 1),
                                                                                                                               (482565, 5, 4, '482565-12HỒNG-M', 990000, 792000, 70, 1),
                                                                                                                               (482565, 2, 5, '482565-00TRẮNG-L', 990000, NULL, 55, 1);

-- Product 482566
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482566, 6, 3, '482566-32BE-S', 1190000, 952000, 50, 1),
                                                                                                                               (482566, 3, 4, '482566-69NAVY-M', 1190000, 952000, 55, 1),
                                                                                                                               (482566, 1, 4, '482566-09ĐEN-M', 1190000, NULL, 45, 1);

-- Product 482567
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482567, 9, 2, '482567-18ĐỎ-XS', 190000, 152000, 100, 1),
                                                                                                                               (482567, 8, 3, '482567-66XANHDUONG-S', 190000, 152000, 110, 1),
                                                                                                                               (482567, 10, 2, '482567-54XANHLA-XS', 190000, NULL, 95, 1);

-- Product 482568
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482568, 3, 3, '482568-69NAVY-S', 390000, NULL, 85, 1),
                                                                                                                               (482568, 1, 4, '482568-09ĐEN-M', 390000, 312000, 90, 1),
                                                                                                                               (482568, 4, 3, '482568-03XÁM-S', 390000, NULL, 80, 1);

-- Product 482569
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482569, 9, 3, '482569-18ĐỎ-S', 790000, 632000, 70, 1),
                                                                                                                               (482569, 3, 4, '482569-69NAVY-M', 790000, 632000, 75, 1),
                                                                                                                               (482569, 1, 3, '482569-09ĐEN-S', 790000, NULL, 65, 1);

-- Product 482570
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482570, 1, 2, '482570-09ĐEN-XS', 290000, NULL, 100, 1),
                                                                                                                               (482570, 4, 3, '482570-03XÁM-S', 290000, 232000, 95, 1),
                                                                                                                               (482570, 3, 2, '482570-69NAVY-XS', 290000, NULL, 90, 1);

-- Product 482571
INSERT INTO product_skus (product_id, color_id, size_id, sku_code, original_price, sale_price, stock_quantity, created_by) VALUES
                                                                                                                               (482571, 4, 3, '482571-03XÁM-S', 490000, 392000, 85, 1),
                                                                                                                               (482571, 1, 4, '482571-09ĐEN-M', 490000, 392000, 90, 1),
                                                                                                                               (482571, 3, 3, '482571-69NAVY-S', 490000, NULL, 80, 1);

-- ==========================================================
-- 7. INSERT PRODUCT IMAGES (3 images per product-color combo)
-- ==========================================================
INSERT INTO product_images (product_id, color_id, image_url, is_main, sort_order) VALUES
                                                                                      (482557, 1, '/images/482557_den_1.jpg', TRUE, 1),
                                                                                      (482557, 1, '/images/482557_den_2.jpg', FALSE, 2),
                                                                                      (482557, 3, '/images/482557_navy_1.jpg', TRUE, 1),
                                                                                      (482557, 3, '/images/482557_navy_2.jpg', FALSE, 2),
                                                                                      (482557, 2, '/images/482557_trang_1.jpg', TRUE, 1),
                                                                                      (482558, 1, '/images/482558_den_1.jpg', TRUE, 1),
                                                                                      (482558, 3, '/images/482558_navy_1.jpg', TRUE, 1),
                                                                                      (482558, 4, '/images/482558_xam_1.jpg', TRUE, 1),
                                                                                      (482559, 3, '/images/482559_navy_1.jpg', TRUE, 1),
                                                                                      (482559, 1, '/images/482559_den_1.jpg', TRUE, 1),
                                                                                      (482559, 4, '/images/482559_xam_1.jpg', TRUE, 1),
                                                                                      (482560, 3, '/images/482560_navy_1.jpg', TRUE, 1),
                                                                                      (482560, 4, '/images/482560_xam_1.jpg', TRUE, 1),
                                                                                      (482560, 1, '/images/482560_den_1.jpg', TRUE, 1),
                                                                                      (482561, 2, '/images/482561_trang_1.jpg', TRUE, 1),
                                                                                      (482561, 3, '/images/482561_navy_1.jpg', TRUE, 1),
                                                                                      (482561, 8, '/images/482561_xanhduong_1.jpg', TRUE, 1),
                                                                                      (482562, 2, '/images/482562_trang_1.jpg', TRUE, 1),
                                                                                      (482562, 1, '/images/482562_den_1.jpg', TRUE, 1),
                                                                                      (482562, 5, '/images/482562_hong_1.jpg', TRUE, 1),
                                                                                      (482563, 3, '/images/482563_navy_1.jpg', TRUE, 1),
                                                                                      (482563, 1, '/images/482563_den_1.jpg', TRUE, 1),
                                                                                      (482563, 4, '/images/482563_xam_1.jpg', TRUE, 1),
                                                                                      (482564, 1, '/images/482564_den_1.jpg', TRUE, 1),
                                                                                      (482564, 6, '/images/482564_be_1.jpg', TRUE, 1),
                                                                                      (482564, 3, '/images/482564_navy_1.jpg', TRUE, 1),
                                                                                      (482565, 1, '/images/482565_den_1.jpg', TRUE, 1),
                                                                                      (482565, 5, '/images/482565_hong_1.jpg', TRUE, 1),
                                                                                      (482565, 2, '/images/482565_trang_1.jpg', TRUE, 1),
                                                                                      (482566, 6, '/images/482566_be_1.jpg', TRUE, 1),
                                                                                      (482566, 3, '/images/482566_navy_1.jpg', TRUE, 1),
                                                                                      (482566, 1, '/images/482566_den_1.jpg', TRUE, 1),
                                                                                      (482567, 9, '/images/482567_do_1.jpg', TRUE, 1),
                                                                                      (482567, 8, '/images/482567_xanhduong_1.jpg', TRUE, 1),
                                                                                      (482567, 10, '/images/482567_xanhla_1.jpg', TRUE, 1),
                                                                                      (482568, 3, '/images/482568_navy_1.jpg', TRUE, 1),
                                                                                      (482568, 1, '/images/482568_den_1.jpg', TRUE, 1),
                                                                                      (482568, 4, '/images/482568_xam_1.jpg', TRUE, 1),
                                                                                      (482569, 9, '/images/482569_do_1.jpg', TRUE, 1),
                                                                                      (482569, 3, '/images/482569_navy_1.jpg', TRUE, 1),
                                                                                      (482569, 1, '/images/482569_den_1.jpg', TRUE, 1),
                                                                                      (482570, 1, '/images/482570_den_1.jpg', TRUE, 1),
                                                                                      (482570, 4, '/images/482570_xam_1.jpg', TRUE, 1),
                                                                                      (482570, 3, '/images/482570_navy_1.jpg', TRUE, 1),
                                                                                      (482571, 4, '/images/482571_xam_1.jpg', TRUE, 1),
                                                                                      (482571, 1, '/images/482571_den_1.jpg', TRUE, 1),
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
                                                                                                             (482557, 2, 1, 5, 'Áo thun chất lượng tuyệt vời, rất thoải mái!', '170cm', '65kg', 'True to size'),
                                                                                                             (482557, 3, 2, 4, 'Chất liệu tốt nhưng màu hơi phai sau khi giặt', '175cm', '70kg', 'True to size'),
                                                                                                             (482558, 1, 4, 5, 'Áo khoác hoàn hảo cho mùa đông, rất ấm!', '180cm', '75kg', 'True to size'),
                                                                                                             (482559, 4, 7, 5, 'Quần jean tốt nhất tôi từng mua!', '165cm', '55kg', 'Runs small'),
                                                                                                             (482560, 5, 10, 4, 'Áo len đẹp nhưng hơi đắt', '172cm', '68kg', 'True to size'),
                                                                                                             (482562, 2, 16, 5, 'Yêu thích tính năng chống UV!', '160cm', '50kg', 'True to size'),
                                                                                                             (482563, 3, 19, 5, 'Co giãn cực tốt và thoải mái', '168cm', '58kg', 'True to size'),
                                                                                                             (482565, 4, 25, 4, 'Váy đầm xinh đẹp, hoàn hảo cho mùa hè', '165cm', '52kg', 'Runs large'),
                                                                                                             (482567, 1, 34, 5, 'Con tôi rất thích chiếc áo này!', '120cm', '25kg', 'True to size'),
                                                                                                             (482569, 5, 40, 5, 'Áo khoác xuất sắc cho trẻ em, rất bền', '130cm', '30kg', 'True to size');