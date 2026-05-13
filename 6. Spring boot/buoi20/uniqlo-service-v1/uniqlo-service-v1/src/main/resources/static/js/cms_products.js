/**
 * QUẢN LÝ SẢN PHẨM - UNIQLO CMS
 * Xử lý logic giao diện, gọi API và tải lên hình ảnh
 */

let currentPage = 0;
const pageSize = 8;
let productModal;

// Dữ liệu dùng chung (Master Data)
let masterData = {
    categories: [],
    users: [],
    colors: [],
    sizes: []
};

document.addEventListener('DOMContentLoaded', () => {
    // Khởi tạo Bootstrap Modal
    productModal = new bootstrap.Modal(document.getElementById('productModal'));
    
    // Tải dữ liệu ban đầu
    loadMasterData();
    fetchProducts();

    // Xử lý sự kiện lọc
    document.getElementById('filterBtn').onclick = () => {
        currentPage = 0;
        fetchProducts();
    };

    // Mở modal thêm sản phẩm
    document.getElementById('addProductBtn').onclick = () => {
        document.getElementById('modalTitle').innerText = 'Thêm sản phẩm mới';
        document.getElementById('productForm').reset();
        document.getElementById('prodId').value = '';
        document.getElementById('skuTableBody').innerHTML = '';
        document.getElementById('imageTableBody').innerHTML = '';
        
        // Reset preview ảnh đại diện
        const preview = document.getElementById('avatarPreview');
        preview.src = '';
        document.getElementById('avatarZone').classList.remove('has-image');
        
        // Chuyển về tab đầu tiên
        bootstrap.Tab.getOrCreateInstance(document.getElementById('basic-tab')).show();
        productModal.show();
    };

    // Lưu sản phẩm
    document.getElementById('saveProductBtn').onclick = saveProduct;
    
    // Xem trước ảnh đại diện khi chọn file
    document.getElementById('prodAvatarFile').onchange = async (e) => {
        const file = e.target.files[0];
        if (file) {
            // Hiển thị preview ngay lập tức bằng FileReader
            const reader = new FileReader();
            reader.onload = (event) => {
                const preview = document.getElementById('avatarPreview');
                preview.src = event.target.result;
                document.getElementById('avatarZone').classList.add('has-image');
            };
            reader.readAsDataURL(file);
        }
    };
});

/**
 * Tải dữ liệu Danh mục, Màu sắc, Kích cỡ từ Server
 */
async function loadMasterData() {
    try {
        const [cats, users, colors, sizes] = await Promise.all([
            fetch('/api/categories').then(r => r.json()),
            fetch('/api/users').then(r => r.json()),
            fetch('/api/colors').then(r => r.json()),
            fetch('/api/sizes').then(r => r.json())
        ]);

        masterData = { categories: cats, users: users, colors: colors, sizes: sizes };

        // Đổ dữ liệu vào các dropdown bộ lọc và trong modal
        const catFilter = document.getElementById('categoryFilter');
        const prodCat = document.getElementById('prodCategory');
        
        cats.forEach(c => {
            const label = c.parentName ? `${c.parentName} > ${c.name}` : c.name;
            const opt = `<option value="${c.id}">${label}</option>`;
            catFilter.insertAdjacentHTML('beforeend', opt);
            prodCat.insertAdjacentHTML('beforeend', opt);
        });

        const userFilter = document.getElementById('creatorFilter');
        users.forEach(u => {
            userFilter.insertAdjacentHTML('beforeend', `<option value="${u.fullName}">${u.fullName}</option>`);
        });
    } catch (e) {
        console.error('Lỗi khi tải dữ liệu nền:', e);
    }
}

/**
 * Lấy danh sách sản phẩm theo bộ lọc và phân trang
 */
async function fetchProducts(page = 0) {
    currentPage = page;
    const keyword = document.getElementById('searchInput').value;
    const catId = document.getElementById('categoryFilter').value;
    const creator = document.getElementById('creatorFilter').value;

    const url = `/api/products?page=${page}&size=${pageSize}&keyword=${encodeURIComponent(keyword)}&categoryId=${catId}&createdBy=${encodeURIComponent(creator)}`;
    
    try {
        const res = await fetch(url);
        const data = await res.json();
        renderTable(data.content);
        renderPagination(data);
    } catch (e) {
        console.error('Lỗi khi tải danh sách sản phẩm:', e);
    }
}

/**
 * Hiển thị dữ liệu vào bảng danh sách
 */
function renderTable(products) {
    const tbody = document.getElementById('productTableBody');
    tbody.innerHTML = '';

    if (products.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7" class="text-center py-5 text-muted">Không tìm thấy sản phẩm nào khớp với bộ lọc</td></tr>';
        return;
    }

    products.forEach(p => {
        const row = `
            <tr>
                <td>
                    <div class="prod-avatar">
                        ${p.avatar ? `<img src="${p.avatar}" alt="${p.name}"/>` : `<span>${p.name.substring(0,2).toUpperCase()}</span>`}
                    </div>
                </td>
                <td><span class="pill"># ${p.id}</span></td>
                <td>
                    <div style="font-weight:700;">${p.name}</div>
                    <div style="font-size:.75rem;color:var(--muted);">${p.description ? (p.description.length > 50 ? p.description.substring(0, 50) + '...' : p.description) : 'Chưa có mô tả'}</div>
                </td>
                <td><span class="pill pill-cat">${p.categoryName || 'N/A'}</span></td>
                <td>${formatDate(p.createdAt)}</td>
                <td>${formatDate(p.updatedAt)}</td>
                <td class="text-end">
                    <div class="action-group">
                        <button class="btn-act btn-act-edit" onclick="editProduct(${p.id})"><i class="bi bi-pencil-square"></i> Sửa</button>
                        <button class="btn-act btn-act-del" onclick="deleteProduct(${p.id})"><i class="bi bi-trash"></i> Xóa</button>
                    </div>
                </td>
            </tr>
        `;
        tbody.insertAdjacentHTML('beforeend', row);
    });
}

/**
 * Hiển thị thanh phân trang
 */
function renderPagination(data) {
    const wrap = document.getElementById('paginationWrap');
    const start = data.number * data.size + 1;
    const end = Math.min((data.number + 1) * data.size, data.totalElements);
    
    let html = `<span>Đang hiển thị ${start}-${end} trên tổng số ${data.totalElements} sản phẩm</span>`;
    html += `<div class="pag">`;
    html += `<button class="pag-btn ${data.first ? 'disabled' : ''}" onclick="fetchProducts(${data.number - 1})"><i class="bi bi-chevron-left"></i></button>`;
    
    for (let i = 0; i < data.totalPages; i++) {
        html += `<button class="pag-btn ${i === data.number ? 'active' : ''}" onclick="fetchProducts(${i})">${i + 1}</button>`;
    }
    
    html += `<button class="pag-btn ${data.last ? 'disabled' : ''}" onclick="fetchProducts(${data.number + 1})"><i class="bi bi-chevron-right"></i></button>`;
    html += `</div>`;
    wrap.innerHTML = html;
}

/**
 * Thêm một dòng biến thể SKU mới
 */
function addSkuRow(data = {}) {
    const tbody = document.getElementById('skuTableBody');
    const rowId = 'sku-' + Math.random().toString(36).substr(2, 9);
    const row = `
        <tr id="${rowId}">
            <td>
                <select class="form-select-s sku-color" required>
                    <option value="">Chọn Màu</option>
                    ${masterData.colors.map(c => `<option value="${c.id}" ${data.colorId == c.id ? 'selected' : ''}>${c.colorCode}</option>`).join('')}
                </select>
            </td>
            <td>
                <select class="form-select-s sku-size" required>
                    <option value="">Size</option>
                    ${masterData.sizes.map(s => `<option value="${s.id}" ${data.sizeId == s.id ? 'selected' : ''}>${s.sizeCode}</option>`).join('')}
                </select>
            </td>
            <td><input type="number" class="input-field sku-orig" value="${data.originalPrice || ''}" required placeholder="0"/></td>
            <td><input type="number" class="input-field sku-sale" value="${data.salePrice || ''}" placeholder="0"/></td>
            <td><input type="number" class="input-field sku-stock" value="${data.stockQuantity || 0}" required/></td>
            <td><input type="text" class="input-field sku-code" value="${data.skuCode || ''}" placeholder="Mã SKU..."/></td>
            <td><button type="button" class="btn btn-sm text-danger" onclick="document.getElementById('${rowId}').remove()"><i class="bi bi-trash"></i></button></td>
        </tr>
    `;
    tbody.insertAdjacentHTML('beforeend', row);
}

/**
 * Thêm một dòng hình ảnh chi tiết mới
 */
function addImageRow(data = {}) {
    const tbody = document.getElementById('imageTableBody');
    const rowId = 'img-' + Math.random().toString(36).substr(2, 9);
    const row = `
        <tr id="${rowId}">
            <td>
                <select class="form-select-s img-color" required>
                    <option value="">Chọn Màu</option>
                    ${masterData.colors.map(c => `<option value="${c.id}" ${data.colorId == c.id ? 'selected' : ''}>${c.colorCode}</option>`).join('')}
                </select>
            </td>
            <td>
                <div class="d-flex flex-column gap-2">
                    <div class="d-flex gap-2 align-items-center">
                        <input type="text" class="input-field img-url" value="${data.imageUrl || ''}" required placeholder="Dán URL hoặc tải ảnh lên..."/>
                        <input type="file" class="d-none img-file" onchange="uploadRowImage(this, '${rowId}')"/>
                        <button type="button" class="btn btn-sm btn-outline-secondary" style="white-space:nowrap;" onclick="this.previousElementSibling.click()">Tải lên</button>
                    </div>
                    <div class="img-preview-row" style="display: ${data.imageUrl ? 'block' : 'none'};">
                        <img src="${data.imageUrl || ''}" class="rounded shadow-sm" style="height: 80px; object-fit: contain; border: 1px solid #eee;"/>
                    </div>
                </div>
            </td>
            <td class="text-center"><input type="checkbox" class="form-check-input img-main" ${data.isMain ? 'checked' : ''}/></td>
            <td><input type="number" class="input-field img-order" value="${data.sortOrder || 0}"/></td>
            <td><button type="button" class="btn btn-sm text-danger" onclick="document.getElementById('${rowId}').remove()"><i class="bi bi-trash"></i></button></td>
        </tr>
    `;
    tbody.insertAdjacentHTML('beforeend', row);
}

/**
 * Tải ảnh lên cho từng dòng trong bảng hình ảnh
 */
async function uploadRowImage(input, rowId) {
    const file = input.files[0];
    if (!file) return;
    
    try {
        const formData = new FormData();
        formData.append('file', file);
        
        const res = await fetch('/api/files/upload', { method: 'POST', body: formData });
        if (res.ok) {
            const url = await res.text();
            const row = document.getElementById(rowId);
            row.querySelector('.img-url').value = url;
            
            const previewWrap = row.querySelector('.img-preview-row');
            const previewImg = previewWrap.querySelector('img');
            previewImg.src = url;
            previewWrap.style.display = 'block';
        }
    } catch (e) {
        Swal.fire('Lỗi', 'Không thể tải ảnh lên hệ thống', 'error');
    }
}

/**
 * Hàm hỗ trợ tải file lên server
 */
async function uploadFile(file) {
    const formData = new FormData();
    formData.append('file', file);
    try {
        const res = await fetch('/api/files/upload', { method: 'POST', body: formData });
        if (res.ok) return await res.text();
    } catch (e) {
        console.error('Lỗi upload:', e);
    }
    return null;
}

/**
 * Lưu toàn bộ thông tin sản phẩm
 */
async function saveProduct() {
    const id = document.getElementById('prodId').value;
    
    // Tải ảnh đại diện nếu người dùng vừa chọn file mới
    let avatarUrl = document.getElementById('prodAvatar').value;
    const avatarFile = document.getElementById('prodAvatarFile').files[0];
    if (avatarFile) {
        Swal.fire({ title: 'Đang tải ảnh...', allowOutsideClick: false, didOpen: () => Swal.showLoading() });
        const uploaded = await uploadFile(avatarFile);
        if (uploaded) avatarUrl = uploaded;
        Swal.close();
    }

    // Thu thập dữ liệu biến thể
    const skus = Array.from(document.querySelectorAll('#skuTableBody tr')).map(tr => ({
        colorId: tr.querySelector('.sku-color').value,
        sizeId: tr.querySelector('.sku-size').value,
        originalPrice: tr.querySelector('.sku-orig').value,
        salePrice: tr.querySelector('.sku-sale').value,
        stockQuantity: tr.querySelector('.sku-stock').value,
        skuCode: tr.querySelector('.sku-code').value
    }));

    // Thu thập dữ liệu hình ảnh
    const images = Array.from(document.querySelectorAll('#imageTableBody tr')).map(tr => ({
        colorId: tr.querySelector('.img-color').value,
        imageUrl: tr.querySelector('.img-url').value,
        isMain: tr.querySelector('.img-main').checked,
        sortOrder: tr.querySelector('.img-order').value
    }));

    const payload = {
        name: document.getElementById('prodName').value,
        categoryId: document.getElementById('prodCategory').value,
        avatar: avatarUrl,
        description: document.getElementById('prodDescription').value,
        materialInfo: document.getElementById('prodMaterial').value,
        skus: skus,
        images: images
    };

    if (!payload.name || !payload.categoryId) {
        Swal.fire('Thông báo', 'Vui lòng nhập Tên sản phẩm và chọn Danh mục', 'warning');
        return;
    }

    try {
        const url = id ? `/api/products/${id}` : '/api/products';
        const method = id ? 'PUT' : 'POST';
        
        const res = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (res.ok) {
            Swal.fire('Thành công', 'Dữ liệu sản phẩm đã được lưu trữ', 'success');
            productModal.hide();
            fetchProducts(currentPage);
        } else {
            const errorData = await res.json();
            Swal.fire('Lỗi hệ thống', errorData.message || 'Không thể lưu sản phẩm. Vui lòng kiểm tra lại dữ liệu.', 'error');
        }
    } catch (e) {
        Swal.fire('Lỗi kết nối', 'Không thể kết nối tới máy chủ API', 'error');
    }
}

/**
 * Tải thông tin sản phẩm vào Modal để chỉnh sửa
 */
async function editProduct(id) {
    try {
        const res = await fetch(`/api/products/${id}`);
        const p = await res.json();
        
        document.getElementById('modalTitle').innerText = 'Chỉnh sửa sản phẩm';
        document.getElementById('prodId').value = p.id;
        document.getElementById('prodName').value = p.name;
        document.getElementById('prodCategory').value = p.categoryId;
        document.getElementById('prodAvatar').value = p.avatar || '';
        document.getElementById('prodDescription').value = p.description || '';
        document.getElementById('prodMaterial').value = p.materialInfo || '';
        
        // Cập nhật preview ảnh đại diện
        const preview = document.getElementById('avatarPreview');
        if (p.avatar) {
            preview.src = p.avatar;
            document.getElementById('avatarZone').classList.add('has-image');
        } else {
            preview.src = '';
            document.getElementById('avatarZone').classList.remove('has-image');
        }
        
        // Đổ dữ liệu SKUs
        document.getElementById('skuTableBody').innerHTML = '';
        if (p.skus) p.skus.forEach(s => addSkuRow(s));
        
        // Đổ dữ liệu hình ảnh
        document.getElementById('imageTableBody').innerHTML = '';
        if (p.images) p.images.forEach(img => addImageRow(img));

        bootstrap.Tab.getOrCreateInstance(document.getElementById('basic-tab')).show();
        productModal.show();
    } catch (e) {
        Swal.fire('Lỗi', 'Không thể tải thông tin chi tiết sản phẩm', 'error');
    }
}

/**
 * Xóa sản phẩm (Soft Delete)
 */
async function deleteProduct(id) {
    const result = await Swal.fire({
        title: 'Xác nhận xóa?',
        text: "Sản phẩm này sẽ được đưa vào trạng thái ngừng kinh doanh!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#e50012',
        confirmButtonText: 'Đồng ý xóa',
        cancelButtonText: 'Hủy bỏ'
    });

    if (result.isConfirmed) {
        try {
            const res = await fetch(`/api/products/${id}`, { method: 'DELETE' });
            if (res.ok) {
                Swal.fire('Đã xóa', 'Sản phẩm đã được cập nhật trạng thái xóa.', 'success');
                fetchProducts(currentPage);
            }
        } catch (e) {
            Swal.fire('Lỗi', 'Quá trình xóa gặp trục trặc', 'error');
        }
    }
}

/**
 * Định dạng ngày tháng
 */
function formatDate(dateStr) {
    if (!dateStr) return '—';
    const d = new Date(dateStr);
    return d.toLocaleDateString('vi-VN');
}
