create table users
(
    id            int auto_increment
        primary key,
    full_name     varchar(100)                                null,
    email         varchar(255)                                not null,
    password_hash varchar(255)                                not null,
    birthday      date                                        null,
    gender        enum ('Male', 'Female', 'Decline to state') null,
    created_at    timestamp default CURRENT_TIMESTAMP         null,
    updated_at    timestamp default CURRENT_TIMESTAMP         null on update CURRENT_TIMESTAMP,
    constraint email
        unique (email)
);

create table categories
(
    id         int auto_increment
        primary key,
    name       varchar(255)                        not null,
    parent_id  int                                 null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    created_by int                                 null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    updated_by int                                 null,
    constraint categories_ibfk_1
        foreign key (parent_id) references categories (id),
    constraint categories_ibfk_2
        foreign key (created_by) references users (id)
);

create index created_by
    on categories (created_by);

create index parent_id
    on categories (parent_id);

create table colors
(
    id         int auto_increment
        primary key,
    color_code varchar(50)                         not null,
    hex_code   varchar(10)                         null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    created_by int                                 null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    updated_by int                                 null,
    constraint colors_ibfk_1
        foreign key (created_by) references users (id)
);

create index created_by
    on colors (created_by);

create table products
(
    id            int                                 not null
        primary key,
    category_id   int                                 not null,
    name          varchar(255)                        not null,
    description   text                                null,
    material_info text                                null,
    created_at    timestamp default CURRENT_TIMESTAMP null,
    created_by    int                                 null,
    updated_at    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    updated_by    int                                 null,
    avatar        text                                null,
    constraint products_ibfk_1
        foreign key (category_id) references categories (id),
    constraint products_ibfk_2
        foreign key (created_by) references users (id)
);

create table product_images
(
    id         int auto_increment
        primary key,
    product_id int                                  not null,
    color_id   int                                  not null,
    image_url  varchar(255)                         not null,
    is_main    tinyint(1) default 0                 null,
    sort_order int        default 0                 null,
    created_at timestamp  default CURRENT_TIMESTAMP null,
    updated_at timestamp  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint product_images_ibfk_1
        foreign key (product_id) references products (id),
    constraint product_images_ibfk_2
        foreign key (color_id) references colors (id)
);

create index color_id
    on product_images (color_id);

create index product_id
    on product_images (product_id);

create index category_id
    on products (category_id);

create index created_by
    on products (created_by);

create table sizes
(
    id         int auto_increment
        primary key,
    size_code  varchar(20)                         not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    created_by int                                 null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    updated_by int                                 null,
    constraint sizes_ibfk_1
        foreign key (created_by) references users (id)
);

create table product_skus
(
    id             int auto_increment
        primary key,
    product_id     int                                 not null,
    color_id       int                                 not null,
    size_id        int                                 not null,
    sku_code       varchar(100)                        null,
    original_price decimal(15, 2)                      not null,
    sale_price     decimal(15, 2)                      null,
    stock_quantity int       default 0                 null,
    created_at     timestamp default CURRENT_TIMESTAMP null,
    created_by     int                                 null,
    updated_at     timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    updated_by     int                                 null,
    constraint sku_code
        unique (sku_code),
    constraint product_skus_ibfk_1
        foreign key (product_id) references products (id),
    constraint product_skus_ibfk_2
        foreign key (color_id) references colors (id),
    constraint product_skus_ibfk_3
        foreign key (size_id) references sizes (id),
    constraint product_skus_ibfk_4
        foreign key (created_by) references users (id)
);

create table cart_items
(
    id         int auto_increment
        primary key,
    user_id    int                                 not null,
    sku_id     int                                 not null,
    quantity   int       default 1                 not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint cart_items_ibfk_1
        foreign key (user_id) references users (id),
    constraint cart_items_ibfk_2
        foreign key (sku_id) references product_skus (id)
);

create index sku_id
    on cart_items (sku_id);

create index user_id
    on cart_items (user_id);

create index color_id
    on product_skus (color_id);

create index created_by
    on product_skus (created_by);

create index product_id
    on product_skus (product_id);

create index size_id
    on product_skus (size_id);

create table reviews
(
    id          int auto_increment
        primary key,
    product_id  int                                 not null,
    user_id     int                                 not null,
    sku_id      int                                 null,
    rating      tinyint                             not null,
    comment     text                                null,
    user_height varchar(50)                         null,
    user_weight varchar(50)                         null,
    fit_status  varchar(50)                         null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    updated_at  timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint reviews_ibfk_1
        foreign key (product_id) references products (id),
    constraint reviews_ibfk_2
        foreign key (user_id) references users (id),
    constraint reviews_ibfk_3
        foreign key (sku_id) references product_skus (id),
    check (`rating` between 1 and 5)
);

create index product_id
    on reviews (product_id);

create index sku_id
    on reviews (sku_id);

create index user_id
    on reviews (user_id);

create index created_by
    on sizes (created_by);

