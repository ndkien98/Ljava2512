<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Uniqlo - Premium Collection</title>
            <!-- Google Fonts -->
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700&display=swap"
                rel="stylesheet">
            <!-- Bootstrap 5 CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Icons -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

            <style>
                :root {
                    --primary-color: #e50012;
                    /* Uniqlo red */
                    --text-color: #1a1a1a;
                    --bg-color: #f7f7f7;
                    --card-bg: #ffffff;
                    --transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
                }

                body {
                    font-family: 'Outfit', sans-serif;
                    background-color: var(--bg-color);
                    color: var(--text-color);
                    -webkit-font-smoothing: antialiased;
                }

                /* Navbar */
                .navbar {
                    background-color: white !important;
                    box-shadow: 0 2px 15px rgba(0, 0, 0, 0.05);
                    padding: 1rem 0;
                    backdrop-filter: blur(10px);
                    background: rgba(255, 255, 255, 0.95) !important;
                }

                .navbar-brand {
                    font-weight: 700;
                    font-size: 1.5rem;
                    letter-spacing: -0.5px;
                    color: var(--primary-color) !important;
                }

                /* Hero Section */
                .hero {
                    position: relative;
                    background: linear-gradient(135deg, #1a1a1a 0%, #3a3a3a 100%);
                    color: white;
                    padding: 100px 0 80px;
                    margin-bottom: 3rem;
                    overflow: hidden;
                }

                .hero::after {
                    content: '';
                    position: absolute;
                    top: 0;
                    left: 0;
                    right: 0;
                    bottom: 0;
                    background: url('https://images.unsplash.com/photo-1441986300917-64674bd600d8?auto=format&fit=crop&q=80') center/cover;
                    opacity: 0.15;
                    mix-blend-mode: overlay;
                }

                .hero-content {
                    position: relative;
                    z-index: 1;
                }

                .hero h1 {
                    font-size: 3.5rem;
                    font-weight: 700;
                    margin-bottom: 1rem;
                    letter-spacing: -1px;
                }

                .hero p {
                    font-size: 1.2rem;
                    font-weight: 300;
                    opacity: 0.9;
                }

                /* Product Cards */
                .product-card {
                    background: var(--card-bg);
                    border-radius: 16px;
                    border: none;
                    overflow: hidden;
                    transition: var(--transition);
                    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.03);
                    height: 100%;
                    text-decoration: none;
                    color: inherit;
                    display: flex;
                    flex-direction: column;
                }

                .product-card:hover {
                    transform: translateY(-8px);
                    box-shadow: 0 12px 30px rgba(0, 0, 0, 0.08);
                }

                .card-img-wrapper {
                    position: relative;
                    padding-top: 125%;
                    /* 4:5 Aspect Ratio */
                    overflow: hidden;
                    background-color: #f1f1f1;
                }

                .card-img-top {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                    transition: transform 0.5s ease;
                }

                .product-card:hover .card-img-top {
                    transform: scale(1.05);
                }

                .category-badge {
                    position: absolute;
                    top: 15px;
                    left: 15px;
                    background: rgba(255, 255, 255, 0.9);
                    padding: 6px 12px;
                    border-radius: 20px;
                    font-size: 0.75rem;
                    font-weight: 600;
                    text-transform: uppercase;
                    letter-spacing: 1px;
                    z-index: 2;
                    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
                }

                .card-body {
                    padding: 1.5rem;
                    display: flex;
                    flex-direction: column;
                    flex-grow: 1;
                }

                .card-title {
                    font-size: 1.1rem;
                    font-weight: 600;
                    margin-bottom: 0.5rem;
                    line-height: 1.4;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    overflow: hidden;
                }

                .card-text {
                    color: #666;
                    font-size: 0.9rem;
                    margin-bottom: 1rem;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    overflow: hidden;
                }

                .btn-view {
                    margin-top: auto;
                    background: transparent;
                    color: var(--text-color);
                    border: 1px solid #e0e0e0;
                    padding: 10px;
                    border-radius: 8px;
                    font-weight: 500;
                    transition: var(--transition);
                    text-align: center;
                }

                .product-card:hover .btn-view {
                    background: var(--text-color);
                    color: white;
                    border-color: var(--text-color);
                }

                /* Empty State */
                .empty-state {
                    padding: 80px 0;
                    text-align: center;
                    color: #666;
                }

                .empty-state i {
                    font-size: 4rem;
                    color: #ccc;
                    margin-bottom: 1rem;
                }
            </style>
        </head>

        <body>

            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg sticky-top">
                <div class="container">
                    <a class="navbar-brand" href="<c:url value='/products'/>">
                        UNIQLO <span class="text-dark">Collection</span>
                    </a>
                </div>
            </nav>

            <!-- Hero -->
            <section class="hero text-center">
                <div class="container hero-content">
                    <h1>LifeWear Collection</h1>
                    <p>Khám phá phong cách giản đơn nhưng không kém phần đẳng cấp.</p>
                </div>
            </section>

            <!-- Content -->
            <div class="container mb-5">
                <div class="row mb-4 align-items-center">
                    <div class="col">
                        <h3 class="fw-bold m-0">Sản phẩm nổi bật</h3>
                    </div>
                    <div class="col-auto text-muted">
                        <c:out value="${products.size()}" /> items
                    </div>
                </div>

                <c:choose>
                    <c:when test="${not empty products}">
                        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
                            <c:forEach var="product" items="${products}">
                                <div class="col">
                                    <a href="<c:url value='/products/${product.id}'/>"
                                        class="product-card text-decoration-none">
                                        <div class="card-img-wrapper">
                                            <c:if test="${not empty product.categoryName}">
                                                <span class="category-badge">${product.categoryName}</span>
                                            </c:if>
                                            <c:choose>
                                                <c:when test="${not empty product.avatar}">
                                                    <img src="${product.avatar}" class="card-img-top"
                                                        alt="${product.name}">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=600&q=80"
                                                        class="card-img-top" alt="Placeholder">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="card-body">
                                            <h5 class="card-title">${product.name}</h5>
                                            <p class="card-text">${product.description}</p>
                                            <div class="btn-view">Xem chi tiết <i class="bi bi-arrow-right ms-1"></i>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <i class="bi bi-inbox"></i>
                            <h3>Chưa có sản phẩm nào!</h3>
                            <p>Có vẻ như bảng products trong database đang trống.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </body>

        </html>