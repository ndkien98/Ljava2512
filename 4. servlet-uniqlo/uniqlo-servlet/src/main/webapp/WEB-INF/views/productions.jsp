<%--
  Created by IntelliJ IDEA.
  User: SKY
  Date: 3/16/2026
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--sử dụng for trên jsp hoặc các toán tử switch case, if else--%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Sản Phẩm - Full CRUD</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold text-primary"><i class="fas fa-boxes me-2"></i>Danh Sách Sản Phẩm</h2>
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">
            <i class="fas fa-plus me-1"></i> Thêm Sản Phẩm
        </button>
    </div>

    <!-- Bộ lọc tìm kiếm -->
    <div class="card mb-4 border-0 shadow-sm">
        <div class="card-body">
            <form id="filterForm" class="row g-3" onsubmit="event.preventDefault(); handlePageChange(1);">
                <div class="col-md-4">
                    <label class="form-label">Từ khóa</label>
                    <input type="text" class="form-control" id="searchKeyword" placeholder="Nhập tên sản phẩm...">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Danh mục</label>
                    <select class="form-select" id="filterCategory"><option value="">Tất cả</option><option value="1">Áo Thun</option><option value="2">Quần Jeans</option><option value="3">Giày Sneaker</option><option value="4">Phụ Kiện</option></select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Màu sắc</label>
                    <select class="form-select" id="filterColor">
                        <option value="">Tất cả</option>
                        <option value="Red">Đỏ</option>
                        <option value="Blue">Xanh</option>
                        <option value="Black">Đen</option>
                        <option value="White">Trắng</option>
                    </select>
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <button type="submit" class="btn btn-dark w-100">
                        <i class="fas fa-search me-1"></i> Tìm
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- Danh sách sản phẩm -->
    <div class="row g-4" id="productList">
        <c:forEach var="p" items="${productions}">
            <div class="col-md-6 col-lg-4">
                <div class="card product-card">
                    <div class="product-img-wrapper">
                        <img src="${p.imageUrl}" alt="${p.description}">
                    </div>
                    <div class="card-body d-flex flex-column">
                        <span class="badge badge-category mb-2 align-self-start">${p.categoryName}</span>
                        <h5 class="card-title text-truncate">${p.name}</h5>
                        <p class="card-text text-muted small flex-grow-1">${p.description}</p>
                        <div class="d-flex justify-content-between align-items-center mt-3">
                            <span class="price-tag">${p.salePrice}</span>
                            <div>
                                <button class="btn btn-sm btn-outline-primary me-1" title="Sửa" onclick="openEditModal(${p.id})">
                                    <i class="fas fa-pen"></i>
                                </button>
                                <button class="btn btn-sm btn-outline-danger" title="Xóa" onclick="deleteProduct(${p.id})">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty productions}">
            <div id="noResult" class="text-center py-5 d-none">
                <i class="fas fa-inbox fa-3x text-muted mb-3"></i>
                <h4 class="text-muted">Không tìm thấy sản phẩm nào</h4>
            </div>
        </c:if>
    </div>
    <!-- Khu vực Phân Trang -->
    <c:if test="${totalPages > 1}">
        <nav aria-label="Page navigation" class="pagination-container">
            <ul class="pagination" id="paginationControl">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="?currentPage=${currentPage - 1}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}">Previous</a>
                </li>
                <c:forEach var="i" begin="1" end="totalPages">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="?currentPage=${i}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${color}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="?currentPage=${currentPage + 1}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}">Next</a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>

<!-- ================= MODAL THÊM SẢN PHẨM ================= -->
<div class="modal fade" id="addProductModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title"><i class="fas fa-plus-circle me-2"></i>Thêm Sản Phẩm Mới</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addProductForm">
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="name" required="">
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Danh mục <span class="text-danger">*</span></label>
                            <select class="form-select" name="category_id" required="" id="modalCategorySelect"><option value="1">Áo Thun</option><option value="2">Quần Jeans</option><option value="3">Giày Sneaker</option><option value="4">Phụ Kiện</option></select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mô tả</label>
                        <textarea class="form-control" name="description" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Chất liệu</label>
                        <textarea class="form-control" name="material_info" rows="2"></textarea>
                    </div>
                    <hr>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label">URL Ảnh</label>
                            <input type="url" class="form-control" name="image_url" value="https://via.placeholder.com/300">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Giá bán</label>
                            <input type="number" class="form-control" name="sale_price" placeholder="0">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-primary" onclick="saveProduct()">Lưu Sản Phẩm</button>
            </div>
        </div>
    </div>
</div>

<!-- ================= MODAL SỬA SẢN PHẨM (MỚI) ================= -->
<div class="modal fade" id="editProductModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-warning text-dark">
                <h5 class="modal-title"><i class="fas fa-edit me-2"></i>Cập Nhật Sản Phẩm</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editProductForm">
                    <input type="hidden" id="editProductId">
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="editName" required="">
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Danh mục <span class="text-danger">*</span></label>
                            <select class="form-select" id="editCategoryId" required=""><option value="1">Áo Thun</option><option value="2">Quần Jeans</option><option value="3">Giày Sneaker</option><option value="4">Phụ Kiện</option></select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mô tả</label>
                        <textarea class="form-control" id="editDescription" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Chất liệu</label>
                        <textarea class="form-control" id="editMaterialInfo" rows="2"></textarea>
                    </div>
                    <hr>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label class="form-label">URL Ảnh</label>
                            <input type="url" class="form-control" id="editImageUrl">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Giá bán</label>
                            <input type="number" class="form-control" id="editPrice">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button type="button" class="btn btn-warning text-dark" onclick="updateProduct()">Cập Nhật</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body></html>