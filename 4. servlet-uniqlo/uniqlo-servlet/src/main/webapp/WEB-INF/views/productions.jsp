<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Sản Phẩm - Uniqlo Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        body { background: #f4f6fb; }
        .page-header { background: linear-gradient(135deg,#1a1a2e 0%,#16213e 60%,#0f3460 100%); color:#fff; border-radius:16px; padding:28px 32px; margin-bottom:28px; }
        .page-header h2 { font-weight:700; font-size:1.7rem; margin:0; }
        .filter-card { border:none; border-radius:14px; box-shadow:0 2px 12px rgba(0,0,0,.08); }
        .product-card { border:none; border-radius:14px; box-shadow:0 2px 12px rgba(0,0,0,.08); overflow:hidden; transition:transform .2s,box-shadow .2s; height:100%; }
        .product-card:hover { transform:translateY(-4px); box-shadow:0 8px 28px rgba(0,0,0,.14); }
        .product-img-wrapper { height:240px; background:#f0f0f0; overflow:hidden; display:flex; align-items:center; justify-content:center; }
        .product-img-wrapper img { width:100%; height:100%; object-fit:cover; transition:transform .3s; }
        .product-card:hover .product-img-wrapper img { transform:scale(1.06); }
        .badge-category { background:#e8f4fd; color:#1565c0; font-size:.74rem; font-weight:600; border-radius:20px; padding:4px 12px; }
        .price-tag { font-size:1.1rem; font-weight:700; color:#e53935; }
        .price-tag .original { font-size:.85rem; font-weight:400; color:#999; text-decoration:line-through; margin-left:6px; }
        .pagination .page-item.active .page-link { background:#0f3460; border-color:#0f3460; }
        .pagination .page-link { color:#0f3460; border-radius:8px; margin:0 2px; }
        .alert-toast { position:fixed; top:20px; right:20px; z-index:9999; min-width:280px; border-radius:12px; }
        .btn-action { width:32px; height:32px; padding:0; display:inline-flex; align-items:center; justify-content:center; border-radius:8px; }
        .modal-header { border-radius:12px 12px 0 0; }
        .no-result-box { text-align:center; padding:60px 0; color:#aaa; }
        .no-result-box i { font-size:3.5rem; margin-bottom:16px; }
    </style>
</head>
<body>

<%-- Toast thông báo --%>
<c:if test="${not empty param.success}">
    <div class="alert alert-success alert-dismissible alert-toast fade show" role="alert">
        <i class="fas fa-check-circle me-2"></i>
        <c:choose>
            <c:when test="${param.success == 'created'}">Thêm sản phẩm thành công!</c:when>
            <c:when test="${param.success == 'updated'}">Cập nhật sản phẩm thành công!</c:when>
            <c:when test="${param.success == 'deleted'}">Xóa sản phẩm thành công!</c:when>
            <c:otherwise>Thao tác thành công!</c:otherwise>
        </c:choose>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>
<c:if test="${not empty param.error}">
    <div class="alert alert-danger alert-dismissible alert-toast fade show" role="alert">
        <i class="fas fa-exclamation-triangle me-2"></i>Có lỗi xảy ra, vui lòng thử lại!
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>

<div class="container py-4">

    <%-- ===== HEADER ===== --%>
    <div class="page-header d-flex justify-content-between align-items-center">
        <div>
            <h2><i class="fas fa-boxes me-2"></i>Quản Lý Sản Phẩm</h2>
            <small class="opacity-75">Tổng: <strong>${totalPages * pageSize}</strong> sản phẩm (ước tính) &bull; Trang ${currentPage}/${totalPages}</small>
        </div>
        <button class="btn btn-light fw-semibold" data-bs-toggle="modal" data-bs-target="#addProductModal">
            <i class="fas fa-plus me-1"></i> Thêm Sản Phẩm
        </button>
    </div>

    <%-- ===== BỘ LỌC TÌM KIẾM ===== --%>
    <div class="card filter-card mb-4">
        <div class="card-body">
            <form method="GET" action="${pageContext.request.contextPath}/productions" class="row g-3 align-items-end">
                <div class="col-md-4">
                    <label class="form-label fw-semibold">Từ khóa</label>
                    <input type="text" class="form-control" name="keySearch" value="${keySearch}" placeholder="Nhập tên sản phẩm...">
                </div>
                <div class="col-md-3">
                    <label class="form-label fw-semibold">Danh mục</label>
                    <select class="form-select" name="categoryId">
                        <option value="-1">-- Tất cả --</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.id}" ${categoryId == cat.id ? 'selected' : ''}>${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label fw-semibold">Màu sắc</label>
                    <select class="form-select" name="colorId">
                        <option value="-1">-- Tất cả --</option>
                        <c:forEach var="col" items="${colors}">
                            <option value="${col.id}" ${colorId == col.id ? 'selected' : ''}>${col.colorCode}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-dark w-100">
                        <i class="fas fa-search me-1"></i> Tìm
                    </button>
                </div>
            </form>
        </div>
    </div>

    <%-- ===== DANH SÁCH SẢN PHẨM ===== --%>
    <div class="row g-4">
        <c:choose>
            <c:when test="${empty productions}">
                <div class="col-12">
                    <div class="no-result-box">
                        <i class="fas fa-inbox d-block"></i>
                        <h4>Không tìm thấy sản phẩm nào</h4>
                        <p class="text-muted">Hãy thử thay đổi bộ lọc hoặc thêm sản phẩm mới.</p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="p" items="${productions}">
                    <div class="col-md-6 col-lg-4">
                        <div class="card product-card">
                            <div class="product-img-wrapper">
                                <img src="${not empty p.imageUrl ? p.imageUrl : 'https://placehold.co/400x500/EEE/333?text=No+Image'}"
                                     alt="${p.name}"
                                     onerror="this.src='https://placehold.co/400x500/EEE/333?text=No+Image'">
                            </div>
                            <div class="card-body d-flex flex-column">
                                <span class="badge badge-category mb-2 align-self-start">${p.categoryName}</span>
                                <h5 class="card-title text-truncate" title="${p.name}">${p.name}</h5>
                                <p class="card-text text-muted small flex-grow-1" style="display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;overflow:hidden;">
                                    ${not empty p.description ? p.description : 'Chưa có mô tả'}
                                </p>
                                <div class="d-flex justify-content-between align-items-center mt-3">
                                    <span class="price-tag">
                                        <c:choose>
                                            <c:when test="${not empty p.salePrice}">
                                                <fmt:formatNumber value="${p.salePrice}" type="number" groupingUsed="true"/>đ
                                            </c:when>
                                            <c:otherwise>Liên hệ</c:otherwise>
                                        </c:choose>
                                    </span>
                                    <div class="d-flex gap-1">
                                        <%-- Nút Sửa: GET tới ?action=edit&id=X --%>
                                        <a href="${pageContext.request.contextPath}/productions?action=edit&id=${p.id}&currentPage=${currentPage}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}"
                                           class="btn btn-sm btn-outline-primary btn-action" title="Sửa">
                                            <i class="fas fa-pen fa-xs"></i>
                                        </a>
                                        <%-- Nút Xóa: POST action=delete --%>
                                        <form method="POST" action="${pageContext.request.contextPath}/productions"
                                              onsubmit="return confirm('Bạn có chắc muốn xóa sản phẩm \'${p.name}\'?');">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="id" value="${p.id}">
                                            <button type="submit" class="btn btn-sm btn-outline-danger btn-action" title="Xóa">
                                                <i class="fas fa-trash fa-xs"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- ===== PHÂN TRANG ===== --%>
    <c:if test="${totalPages > 1}">
        <nav class="mt-4 d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/productions?currentPage=${currentPage - 1}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}">
                        <i class="fas fa-chevron-left fa-xs"></i>
                    </a>
                </li>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/productions?currentPage=${i}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="${pageContext.request.contextPath}/productions?currentPage=${currentPage + 1}&keySearch=${keySearch}&categoryId=${categoryId}&colorId=${colorId}">
                        <i class="fas fa-chevron-right fa-xs"></i>
                    </a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>

<%-- ==================== MODAL THÊM SẢN PHẨM ==================== --%>
<div class="modal fade" id="addProductModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content border-0 shadow">
            <div class="modal-header" style="background:linear-gradient(135deg,#1a1a2e,#0f3460);color:#fff;">
                <h5 class="modal-title"><i class="fas fa-plus-circle me-2"></i>Thêm Sản Phẩm Mới</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/productions">
                <input type="hidden" name="action" value="create">
                <div class="modal-body px-4 py-3">
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label fw-semibold">Tên sản phẩm <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="name" required placeholder="Nhập tên sản phẩm...">
                        </div>
                        <div class="col-md-4">
                            <label class="form-label fw-semibold">Danh mục <span class="text-danger">*</span></label>
                            <select class="form-select" name="category_id" required>
                                <option value="">-- Chọn danh mục --</option>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat.id}">${cat.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Mô tả</label>
                        <textarea class="form-control" name="description" rows="3" placeholder="Nhập mô tả sản phẩm..."></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Thông tin chất liệu</label>
                        <textarea class="form-control" name="material_info" rows="2" placeholder="Ví dụ: 100% Cotton..."></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">URL Ảnh đại diện</label>
                        <input type="text" class="form-control" name="avatar" placeholder="https://...">
                        <div class="form-text">Để trống sẽ dùng ảnh mặc định.</div>
                    </div>
                </div>
                <div class="modal-footer border-0 pt-0">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary px-4">
                        <i class="fas fa-save me-1"></i> Lưu Sản Phẩm
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%-- ==================== MODAL SỬA SẢN PHẨM ==================== --%>
<%-- Modal này chỉ xuất hiện khi có editProduct trong request (action=edit) --%>
<c:if test="${not empty editProduct}">
<div class="modal fade" id="editProductModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content border-0 shadow">
            <div class="modal-header" style="background:linear-gradient(135deg,#f39c12,#e67e22);color:#fff;">
                <h5 class="modal-title"><i class="fas fa-edit me-2"></i>Cập Nhật Sản Phẩm #${editProduct.id}</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/productions">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${editProduct.id}">
                <div class="modal-body px-4 py-3">
                    <div class="row mb-3">
                        <div class="col-md-8">
                            <label class="form-label fw-semibold">Tên sản phẩm <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="name" value="${editProduct.name}" required>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label fw-semibold">Danh mục <span class="text-danger">*</span></label>
                            <select class="form-select" name="category_id" required>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat.id}" ${editProduct.categoryId == cat.id ? 'selected' : ''}>${cat.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Mô tả</label>
                        <textarea class="form-control" name="description" rows="3">${editProduct.description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Thông tin chất liệu</label>
                        <textarea class="form-control" name="material_info" rows="2">${editProduct.materialInfo}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">URL Ảnh đại diện</label>
                        <input type="text" class="form-control" name="avatar" value="${editProduct.imageUrl}">
                    </div>
                </div>
                <div class="modal-footer border-0 pt-0">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-warning text-white px-4">
                        <i class="fas fa-save me-1"></i> Cập Nhật
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%-- Script tự mở modal sửa khi có editProduct --%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var editModal = new bootstrap.Modal(document.getElementById('editProductModal'));
        editModal.show();
    });
</script>
</c:if>

<%-- Bootstrap JS --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%-- Auto-hide toast sau 4 giây --%>
<script>
    setTimeout(function () {
        document.querySelectorAll('.alert-toast').forEach(function (el) {
            var alertInst = bootstrap.Alert.getOrCreateInstance(el);
            if (alertInst) alertInst.close();
        });
    }, 4000);
</script>
</body>
</html>