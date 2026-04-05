<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>${product.name} - Uniqlo</title>
                <!-- Google Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700&display=swap"
                    rel="stylesheet">
                <!-- Bootstrap 5 -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

                <style>
                    :root {
                        --primary-color: #e50012;
                        --text-color: #1a1a1a;
                        --bg-color: #ffffff;
                    }

                    body {
                        font-family: 'Outfit', sans-serif;
                        background-color: var(--bg-color);
                        color: var(--text-color);
                    }

                    .navbar {
                        background-color: white !important;
                        box-shadow: 0 2px 15px rgba(0, 0, 0, 0.05);
                        padding: 1rem 0;
                        margin-bottom: 3rem;
                    }

                    .navbar-brand {
                        font-weight: 700;
                        font-size: 1.5rem;
                        letter-spacing: -0.5px;
                        color: var(--primary-color) !important;
                    }

                    .product-image-container {
                        border-radius: 20px;
                        overflow: hidden;
                        background: #f8f9fa;
                        position: relative;
                    }

                    .product-image {
                        width: 100%;
                        height: auto;
                        object-fit: cover;
                    }

                    .category-label {
                        color: #666;
                        font-weight: 600;
                        text-transform: uppercase;
                        letter-spacing: 1px;
                        font-size: 0.85rem;
                        margin-bottom: 1rem;
                        display: block;
                    }

                    .product-title {
                        font-size: 2.5rem;
                        font-weight: 700;
                        line-height: 1.2;
                        margin-bottom: 1.5rem;
                        letter-spacing: -1px;
                    }

                    .section-title {
                        font-size: 1.1rem;
                        font-weight: 600;
                        margin-bottom: 0.5rem;
                        margin-top: 2rem;
                        border-bottom: 2px solid #eee;
                        padding-bottom: 0.5rem;
                        display: inline-block;
                    }

                    .info-text {
                        color: #555;
                        font-size: 1rem;
                        line-height: 1.7;
                    }

                    .btn-back {
                        display: inline-block;
                        margin-bottom: 2rem;
                        color: #666;
                        text-decoration: none;
                        font-weight: 500;
                        transition: all 0.3s;
                    }

                    .btn-back:hover {
                        color: var(--primary-color);
                        transform: translateX(-5px);
                    }

                    .meta-info {
                        background: #f8f9fa;
                        padding: 1rem;
                        border-radius: 10px;
                        margin-top: 2rem;
                        font-size: 0.85rem;
                        color: #888;
                    }
                </style>
            </head>

            <body>

                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg">
                    <div class="container">
                        <a class="navbar-brand" href="<c:url value='/products'/>">
                            UNIQLO <span class="text-dark">Collection</span>
                        </a>
                    </div>
                </nav>

                <div class="container mb-5">
                    <a href="<c:url value='/products'/>" class="btn-back">
                        <i class="bi bi-arrow-left me-2"></i> Trở về danh sách
                    </a>

                    <div class="row g-5">
                        <!-- Left: Image -->
                        <div class="col-md-6">
                            <div class="product-image-container shadow-sm">
                                <c:choose>
                                    <c:when test="${not empty product.avatar}">
                                        <img src="${product.avatar}" class="product-image" alt="${product.name}">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=800&q=80"
                                            class="product-image" alt="Placeholder">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Right: Info -->
                        <div class="col-md-6">
                            <c:if test="${not empty product.categoryName}">
                                <span class="category-label">${product.categoryName}</span>
                            </c:if>

                            <h1 class="product-title">${product.name}</h1>

                            <div class="mt-4">
                                <h4 class="section-title">Mô tả sản phẩm</h4>
                                <p class="info-text">
                                    <c:choose>
                                        <c:when test="${not empty product.description}">
                                            ${product.description}
                                        </c:when>
                                        <c:otherwise>
                                            <em>Chưa có mô tả cho sản phẩm này.</em>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>

                            <c:if test="${not empty product.materialInfo}">
                                <div>
                                    <h4 class="section-title">Chất liệu & Chăm sóc</h4>
                                    <p class="info-text">${product.materialInfo}</p>
                                </div>
                            </c:if>

                            <div class="meta-info">
                                <strong>ID Sản phẩm:</strong> #${product.id} <br>
                                <strong>Ngày tạo:</strong>
                                <fmt:formatDate value="${product.createdAt}" pattern="dd/MM/yyyy HH:mm" />
                            </div>
                        </div>
                    </div>
                </div>

            </body>

            </html>