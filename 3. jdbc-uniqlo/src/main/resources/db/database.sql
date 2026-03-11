-- Thiết lập Database (Nếu cần)
CREATE DATABASE IF NOT EXISTS uniqlo_education;
USE uniqlo_education;

-- ==========================================================
-- 1. HỆ THỐNG NGƯỜI DÙNG (Để phục vụ Audit Logs)
-- ==========================================================
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       full_name VARCHAR(100),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       birthday DATE,
                       gender ENUM('Male', 'Female', 'Decline to state'),

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================================
-- 2. HỆ THỐNG DANH MỤC & THUỘC TÍNH (MÀU, SIZE)
-- ==========================================================

-- Bảng Danh mục sản phẩm (Men, Women, Innerwear...)
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            parent_id INT NULL,

                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            created_by INT NULL,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            updated_by INT NULL,

                            FOREIGN KEY (parent_id) REFERENCES categories(id),
                            FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Bảng Màu sắc (Một màu có thể dùng cho nhiều sản phẩm)
CREATE TABLE colors (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        color_code VARCHAR(50) NOT NULL, -- Ví dụ: '69 NAVY', '09 BLACK'
                        hex_code VARCHAR(10) NULL,      -- Ví dụ: '#2C3E50'

                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_by INT NULL,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        updated_by INT NULL,

                        FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Bảng Kích thước (Một kích thước có thể dùng cho nhiều sản phẩm)
CREATE TABLE sizes (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       size_code VARCHAR(20) NOT NULL, -- Ví dụ: 'XXS', 'S', 'M', 'L', 'XL'

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       created_by INT NULL,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       updated_by INT NULL,

                       FOREIGN KEY (created_by) REFERENCES users(id)
);

-- ==========================================================
-- 3. HỆ THỐNG SẢN PHẨM & SKU (BIẾN THỂ)
-- ==========================================================

-- Bảng thông tin sản phẩm chung
CREATE TABLE products (
                          id INT PRIMARY KEY, -- Sử dụng Product ID thực tế (vd: 482557)
                          category_id INT NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          material_info TEXT, -- AIRism, HEATTECH...

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          created_by INT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          updated_by INT NULL,

                          FOREIGN KEY (category_id) REFERENCES categories(id),
                          FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Bảng SKU: Tổ hợp (Product + Color + Size)
CREATE TABLE product_skus (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              product_id INT NOT NULL,
                              color_id INT NOT NULL,
                              size_id INT NOT NULL,
                              sku_code VARCHAR(100) UNIQUE, -- vd: 482557-69NAVY-L
                              original_price DECIMAL(15, 2) NOT NULL,
                              sale_price DECIMAL(15, 2) NULL, -- Để quản lý các đợt giảm giá (Ảnh 7)
                              stock_quantity INT DEFAULT 0,

                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              created_by INT NULL,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              updated_by INT NULL,

                              FOREIGN KEY (product_id) REFERENCES products(id),
                              FOREIGN KEY (color_id) REFERENCES colors(id),
                              FOREIGN KEY (size_id) REFERENCES sizes(id),
                              FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Bảng Hình ảnh: Một màu của một sản phẩm có nhiều ảnh (Gallery)
CREATE TABLE product_images (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                product_id INT NOT NULL,
                                color_id INT NOT NULL,
                                image_url VARCHAR(255) NOT NULL,
                                is_main BOOLEAN DEFAULT FALSE, -- Ảnh đại diện khi chọn màu đó
                                sort_order INT DEFAULT 0,

                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                FOREIGN KEY (product_id) REFERENCES products(id),
                                FOREIGN KEY (color_id) REFERENCES colors(id)
);

-- ==========================================================
-- 4. TƯƠNG TÁC: GIỎ HÀNG & ĐÁNH GIÁ
-- ==========================================================

-- Bảng Giỏ hàng
CREATE TABLE cart_items (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            user_id INT NOT NULL,
                            sku_id INT NOT NULL, -- Mua chính xác tổ hợp nào
                            quantity INT NOT NULL DEFAULT 1,

                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (sku_id) REFERENCES product_skus(id)
);

-- Bảng Đánh giá (Review)
CREATE TABLE reviews (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         product_id INT NOT NULL,
                         user_id INT NOT NULL,
                         sku_id INT NULL, -- Người dùng đã mua tổ hợp nào
                         rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
                         comment TEXT,

    -- Thông số người dùng (Đặc thù Uniqlo)
                         user_height VARCHAR(50),
                         user_weight VARCHAR(50),
                         fit_status VARCHAR(50), -- Runs small, True to size, Runs large

                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                         FOREIGN KEY (product_id) REFERENCES products(id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (sku_id) REFERENCES product_skus(id)
);