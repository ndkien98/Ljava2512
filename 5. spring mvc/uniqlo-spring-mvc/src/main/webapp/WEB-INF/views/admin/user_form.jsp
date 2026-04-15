<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin.Users - User Form</title>

  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet" />

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

  <style>
    :root {
      --brand: #e50012;
      --ink: #111827;
      --muted: #6b7280;
      --bg: #f6f7fb;
      --card: #ffffff;
      --ring: rgba(229, 0, 18, .18);
      --bs-border-radius: 0.9rem;
      --bs-border-radius-lg: 1rem;
      --bs-border-radius-sm: 0.7rem;
      --bs-body-color: var(--ink);
      --bs-body-font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
    }

    html, body { height: 100%; }

    body {
      font-family: var(--bs-body-font-family);
      text-rendering: optimizeLegibility;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      line-height: 1.5;
      background:
        radial-gradient(1200px 500px at 20% -10%, rgba(229, 0, 18, 0.10), transparent 60%),
        radial-gradient(900px 500px at 90% 10%, rgba(17, 24, 39, 0.08), transparent 68%),
        var(--bg);
      color: var(--ink);
    }

    .shell { max-width: 960px; }

    .topbar {
      position: sticky;
      top: 0;
      z-index: 1020;
      background: rgba(255,255,255,.85);
      backdrop-filter: blur(10px);
      border-bottom: 1px solid rgba(17, 24, 39, 0.06);
      box-shadow: 0 10px 35px rgba(17, 24, 39, 0.06);
    }

    .brand { font-weight: 900; letter-spacing: -0.6px; }
    .brand .dot { color: var(--brand); }

    .card-elev {
      background: var(--card);
      border: 1px solid rgba(17, 24, 39, 0.06);
      border-radius: 16px;
      box-shadow: 0 18px 45px rgba(17, 24, 39, 0.08);
    }

    .action-btn { border-radius: 12px; font-weight: 800; }

    .btn-brand { background: var(--brand); border-color: var(--brand); }
    .btn-brand:hover { background: #c80010; border-color: #c80010; }

    .form-control, .form-select {
      border-radius: 12px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      padding: 10px 12px;
      background: rgba(255,255,255,.9);
    }

    .btn:focus, .btn:active,
    .form-control:focus, .form-select:focus {
      box-shadow: 0 0 0 .25rem var(--ring) !important;
    }

    .form-control:focus, .form-select:focus { border-color: rgba(229, 0, 18, 0.35); }

    .hint { font-size: .9rem; color: var(--muted); }

    .avatarPreview {
      width: 72px; height: 72px;
      border-radius: 18px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      background: linear-gradient(135deg, rgba(229,0,18,.20), rgba(17,24,39,.06));
      overflow: hidden;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 900;
    }

    .avatarPreview img { width: 100%; height: 100%; object-fit: cover; }

    .form-title { font-weight: 950; letter-spacing: -0.8px; }

    .section {
      border: 1px dashed rgba(17, 24, 39, 0.18);
      border-radius: 16px;
      padding: 16px;
      background: rgba(255,255,255,.6);
    }

    .error-text { color: #b42318; font-weight: 700; font-size: .9rem; }

    @media (max-width: 576px) {
      .form-title { font-size: 1.35rem; }
      .topbar .brand { font-size: 1.15rem !important; }
    }
  </style>
</head>

<body>

  <div class="topbar">
    <div class="container shell py-3 d-flex align-items-center justify-content-between">
      <div class="brand fs-4">Admin<span class="dot">.</span>Users</div>
      <div class="d-flex gap-2">
        <a class="btn btn-outline-dark action-btn" href="${pageContext.request.contextPath}/admin/users">
          <i class="bi bi-arrow-left me-1"></i> Quay lại
        </a>
        <button type="button" class="btn btn-brand text-white action-btn" id="saveBtn">
          <i class="bi bi-check2-circle me-1"></i> Lưu
        </button>
      </div>
    </div>
  </div>

  <div class="container shell my-4">

    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger card-elev border-0" role="alert">
        <i class="bi bi-exclamation-triangle me-2"></i><c:out value="${errorMessage}" />
      </div>
    </c:if>

    <div class="card-elev p-4">
      <div class="d-flex flex-wrap align-items-start justify-content-between gap-3">
        <div>
          <div class="form-title h3 mb-1">
            <c:choose>
              <c:when test="${mode == 'edit'}">Sửa người dùng</c:when>
              <c:otherwise>Tạo người dùng</c:otherwise>
            </c:choose>
          </div>
          <div class="hint">Form UI + Spring MVC mapping.</div>
        </div>

        <div class="d-flex align-items-center gap-3">
          <div class="avatarPreview" id="avatarPreviewBox" title="Preview avatar">
            <span id="avatarInitial">U</span>
            <img id="avatarPreviewImg" src="" alt="avatar" style="display:none" />
          </div>
          <div>
            <div class="fw-bold" id="previewName">
              <c:out value="${empty user.fullName ? 'New User' : user.fullName}" />
            </div>
            <div class="hint">ID: <span class="fw-semibold"><c:out value="${empty user.id ? '(auto)' : user.id}" /></span></div>
          </div>
        </div>
      </div>

      <hr class="my-4" />

      <c:url var="formAction" value="/admin/users/create" />
      <c:if test="${mode == 'edit'}">
        <c:url var="formAction" value="/admin/users/${user.id}/edit" />
      </c:if>

      <form id="userForm" class="row g-3" action="${pageContext.request.contextPath}${formAction}" method="post">

        <div class="col-12">
          <div class="section">
            <div class="fw-bold mb-3"><i class="bi bi-person-vcard me-2"></i>Thông tin cơ bản</div>

            <div class="row g-3">
              <div class="col-12 col-md-6">
                <label class="form-label fw-semibold">Họ tên *</label>
                <input class="form-control" type="text" name="fullName" id="fullName" value="<c:out value='${user.fullName}'/>" placeholder="Ví dụ: Nguyễn Văn A" />
                <c:if test="${not empty errors.fullName}"><div class="error-text mt-1"><c:out value="${errors.fullName}" /></div></c:if>
              </div>

              <div class="col-12 col-md-6">
                <label class="form-label fw-semibold">Email *</label>
                <input class="form-control" type="email" name="email" value="<c:out value='${user.email}'/>" placeholder="name@example.com" />
                <div class="hint mt-1">Sẽ kiểm tra unique email trong database.</div>
                <c:if test="${not empty errors.email}"><div class="error-text mt-1"><c:out value="${errors.email}" /></div></c:if>
              </div>

              <div class="col-12 col-md-4">
                <label class="form-label fw-semibold">Giới tính</label>
                <select class="form-select" name="gender">
                  <option value="" ${empty user.gender ? 'selected' : ''}>— Chọn —</option>
                  <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                  <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                  <option value="Decline to state" ${user.gender == 'Decline to state' ? 'selected' : ''}>Decline to state</option>
                </select>
              </div>

              <div class="col-12 col-md-4">
                <label class="form-label fw-semibold">Ngày sinh</label>
                <input class="form-control" type="date" name="birthday" value="<c:out value='${user.birthday}'/>" />
              </div>

              <div class="col-12 col-md-4">
                <label class="form-label fw-semibold">Role</label>
                <select class="form-select" name="role">
                  <option value="USER" ${empty user.role || user.role == 'USER' ? 'selected' : ''}>USER</option>
                  <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                </select>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12">
          <div class="section">
            <div class="fw-bold mb-3"><i class="bi bi-image me-2"></i>Avatar</div>
            <div class="row g-3 align-items-end">
              <div class="col-12 col-md-9">
                <label class="form-label fw-semibold">Avatar URL</label>
                <input class="form-control" id="avatarInput" type="url" name="avatar" value="<c:out value='${user.avatar}'/>" placeholder="https://..." />
                <div class="hint mt-1">Gợi ý: dùng ảnh square để hiển thị đẹp.</div>
              </div>
              <div class="col-12 col-md-3 d-grid">
                <button type="button" class="btn btn-outline-dark action-btn" id="previewBtn">
                  <i class="bi bi-magic me-1"></i> Preview
                </button>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12">
          <div class="section">
            <div class="fw-bold mb-3"><i class="bi bi-lock me-2"></i>Mật khẩu</div>

            <div class="row g-3">
              <div class="col-12 col-md-6">
                <label class="form-label fw-semibold">Mật khẩu <c:if test="${mode != 'edit'}">*</c:if></label>
                <input class="form-control" type="password" name="password" placeholder="••••••••" />
                <div class="hint mt-1">Edit: để trống để giữ nguyên (serverside).</div>
                <c:if test="${not empty errors.password}"><div class="error-text mt-1"><c:out value="${errors.password}" /></div></c:if>
              </div>

              <div class="col-12 col-md-6">
                <label class="form-label fw-semibold">Nhập lại mật khẩu</label>
                <input class="form-control" type="password" name="confirmPassword" placeholder="••••••••" />
              </div>
            </div>
          </div>
        </div>

        <div class="col-12">
          <div class="d-flex flex-wrap justify-content-between align-items-center gap-2">
            <div class="hint">* Trường bắt buộc. Validate serverside trong Service.</div>

            <div class="d-flex gap-2">
              <a class="btn btn-outline-dark action-btn" href="${pageContext.request.contextPath}/admin/users">Huỷ</a>
              <button type="submit" class="btn btn-brand text-white action-btn">
                <i class="bi bi-check2-circle me-1"></i> Lưu
              </button>
            </div>
          </div>
        </div>

      </form>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    const form = document.getElementById('userForm');
    document.getElementById('saveBtn').addEventListener('click', () => form.submit());

    const fullName = document.getElementById('fullName');
    const previewName = document.getElementById('previewName');
    fullName.addEventListener('input', () => {
      previewName.textContent = fullName.value || 'New User';
      const initial = (fullName.value || 'U').trim().charAt(0).toUpperCase();
      document.getElementById('avatarInitial').textContent = initial || 'U';
    });

    const avatarInput = document.getElementById('avatarInput');
    const img = document.getElementById('avatarPreviewImg');
    const initial = document.getElementById('avatarInitial');

    function applyAvatar(url) {
      if (!url) {
        img.style.display = 'none';
        initial.style.display = 'block';
        img.src = '';
        return;
      }
      img.onload = () => {
        img.style.display = 'block';
        initial.style.display = 'none';
      };
      img.onerror = () => {
        img.style.display = 'none';
        initial.style.display = 'block';
      };
      img.src = url;
    }

    document.getElementById('previewBtn').addEventListener('click', () => applyAvatar(avatarInput.value));
    applyAvatar(avatarInput.value);
  </script>
</body>
</html>

