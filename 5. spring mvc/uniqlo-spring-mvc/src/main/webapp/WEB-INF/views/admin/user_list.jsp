<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin.Users - User Management</title>

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
      font-family: var(--bs-body-font-family), sans-serif;
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
    .shell { max-width: 1200px; }

    .card-elev {
      background: var(--card);
      border: 1px solid rgba(17, 24, 39, 0.06);
      border-radius: 16px;
      box-shadow: 0 18px 45px rgba(17, 24, 39, 0.08);
    }

    .shadow-soft { box-shadow: 0 12px 30px rgba(17, 24, 39, 0.07); }

    .pill {
      display: inline-flex;
      align-items: center;
      gap: 8px;
      padding: 6px 12px;
      border-radius: 999px;
      font-weight: 700;
      font-size: .85rem;
      border: 1px solid rgba(17, 24, 39, 0.08);
      background: rgba(255, 255, 255, 0.65);
    }

    .pill-admin {
      border-color: rgba(229, 0, 18, 0.25);
      background: rgba(229, 0, 18, 0.08);
      color: #b1000d;
    }

    .pill-user {
      border-color: rgba(17, 24, 39, 0.15);
      background: rgba(17, 24, 39, 0.04);
      color: #111827;
    }

    .searchbox {
      border-radius: 12px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      padding: 10px 12px;
      background: rgba(255,255,255,.85);
    }

    .searchbox:focus {
      box-shadow: 0 0 0 .25rem var(--ring);
      border-color: rgba(229, 0, 18, 0.35);
    }

    .btn-brand {
      background: var(--brand);
      border-color: var(--brand);
      border-radius: 12px;
      font-weight: 800;
    }

    .btn-brand:hover { background: #c80010; border-color: #c80010; }

    .table thead th {
      font-size: .8rem;
      text-transform: uppercase;
      letter-spacing: 1px;
      color: var(--muted);
      border-bottom: 1px solid rgba(17, 24, 39, 0.08);
      white-space: nowrap;
    }

    .table tbody td { vertical-align: middle; }
    .table tbody tr:hover { background: rgba(17, 24, 39, 0.02); }

    .avatar {
      width: 42px; height: 42px;
      border-radius: 14px;
      overflow: hidden;
      border: 1px solid rgba(17, 24, 39, 0.08);
      background: linear-gradient(135deg, rgba(229,0,18,.20), rgba(17,24,39,.06));
      display: inline-flex;
      align-items: center;
      justify-content: center;
      font-weight: 900;
      color: rgba(17, 24, 39, 0.8);
    }

    .avatar img { width: 100%; height: 100%; object-fit: cover; }

    .badge-gender {
      border-radius: 999px;
      font-weight: 800;
      font-size: .8rem;
      padding: 6px 10px;
      border: 1px solid rgba(17, 24, 39, 0.10);
      background: rgba(255,255,255,.7);
      color: #111827;
      white-space: nowrap;
    }

    .page-title { font-weight: 900; letter-spacing: -0.8px; }
    .subtle { color: var(--muted); }

    .action-btn { border-radius: 10px; }

    .empty { padding: 70px 20px; text-align: center; color: var(--muted); }
    .empty i { font-size: 56px; color: rgba(17, 24, 39, 0.20); }
  </style>
</head>

<body>

  <div class="topbar">
    <div class="container shell py-3 d-flex align-items-center justify-content-between">
      <div class="d-flex align-items-center gap-3">
        <div class="brand fs-4">Admin<span class="dot">.</span>Users</div>
        <span class="pill"><i class="bi bi-shield-check"></i> User Management</span>
      </div>

      <div class="d-flex align-items-center gap-2">
        <a class="btn btn-outline-dark action-btn" href="${pageContext.request.contextPath}/products" title="Home">
          <i class="bi bi-house"></i>
        </a>
        <a class="btn btn-brand text-white action-btn" href="${pageContext.request.contextPath}/admin/users/create">
          <i class="bi bi-plus-lg me-1"></i> Tạo user
        </a>
      </div>
    </div>
  </div>

  <div class="container shell my-4">

    <c:if test="${not empty successMessage}">
      <div class="alert alert-success card-elev shadow-soft border-0" role="alert">
        <i class="bi bi-check-circle me-2"></i><c:out value="${successMessage}" />
      </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger card-elev shadow-soft border-0" role="alert">
        <i class="bi bi-exclamation-triangle me-2"></i><c:out value="${errorMessage}" />
      </div>
    </c:if>

    <div class="card-elev p-4 mb-4">
      <div class="d-flex flex-wrap align-items-end justify-content-between gap-3">
        <div>
          <div class="page-title h3 mb-1">Danh sách người dùng</div>
          <div class="subtle">Quản lý người dùng (search, filter, CRUD).</div>
        </div>

        <div class="d-flex align-items-center gap-2">
          <span class="pill"><i class="bi bi-people"></i>
            <c:out value="${users.size()}" /> users
          </span>
        </div>
      </div>

      <hr class="my-4" />

      <form class="row g-2 align-items-end" method="get" action="${pageContext.request.contextPath}/admin/users">
        <div class="col-12 col-lg-6">
          <label class="form-label fw-semibold" for="keywordInput">Tìm kiếm</label>
          <div class="input-group">
            <span class="input-group-text" style="border-radius: 12px 0 0 12px; border: 1px solid rgba(17,24,39,.10);">
              <i class="bi bi-search"></i>
            </span>
            <input id="keywordInput" class="form-control searchbox" name="keyword" value="<c:out value='${keyword}'/>" placeholder="Tìm theo họ tên hoặc email..." />
          </div>
        </div>

        <div class="col-12 col-md-6 col-lg-3">
          <label class="form-label fw-semibold" for="roleSelect">Role</label>
          <select id="roleSelect" class="form-select searchbox" name="role">
            <option value="ALL" <c:if test="${empty role || role == 'ALL'}">selected</c:if>>ALL</option>
            <option value="USER" <c:if test="${role == 'USER'}">selected</c:if>>USER</option>
            <option value="ADMIN" <c:if test="${role == 'ADMIN'}">selected</c:if>>ADMIN</option>
          </select>
        </div>

        <div class="col-12 col-md-6 col-lg-3 d-grid">
          <button class="btn btn-brand text-white" type="submit">
            <i class="bi bi-funnel me-1"></i> Áp dụng
          </button>
        </div>
      </form>
    </div>

    <div class="card-elev p-3 p-md-4">
      <div class="table-responsive">
        <table class="table align-middle mb-0">
          <thead>
          <tr>
            <th style="width: 70px;">Avatar</th>
            <th>Full name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Birthday</th>
            <th>Role</th>
            <th>Created</th>
            <th class="text-end">Actions</th>
          </tr>
          </thead>

          <tbody>
          <c:if test="${empty users}">
            <tr>
              <td colspan="8">
                <div class="empty">
                  <i class="bi bi-emoji-frown"></i>
                  <div class="mt-3 fw-bold">Không có user nào</div>
                  <div class="mt-1">Thử đổi bộ lọc hoặc tạo mới.</div>
                </div>
              </td>
            </tr>
          </c:if>

          <c:forEach items="${users}" var="u">
            <tr>
              <td>
                <div class="avatar">
                  <c:choose>
                    <c:when test="${not empty u.avatar}">
                      <img src="<c:out value='${u.avatar}'/>" alt="avatar" />
                    </c:when>
                    <c:otherwise>
                      <span>
                        <c:choose>
                          <c:when test="${not empty u.fullName}"><c:out value="${fn:substring(u.fullName,0,1)}" /></c:when>
                          <c:otherwise>U</c:otherwise>
                        </c:choose>
                      </span>
                    </c:otherwise>
                  </c:choose>
                </div>
              </td>
              <td class="fw-semibold"><c:out value="${u.fullName}" /></td>
              <td><c:out value="${u.email}" /></td>
              <td><span class="badge-gender"><c:out value="${empty u.gender ? '—' : u.gender}" /></span></td>
              <td><c:out value="${empty u.birthday ? '—' : u.birthday}" /></td>
              <td>
                <c:choose>
                  <c:when test="${u.role == 'ADMIN'}">
                    <span class="pill pill-admin"><i class="bi bi-shield-lock"></i> ADMIN</span>
                  </c:when>
                  <c:otherwise>
                    <span class="pill pill-user"><i class="bi bi-person"></i> USER</span>
                  </c:otherwise>
                </c:choose>
              </td>
              <td><c:out value="${empty u.createdAt ? '—' : u.createdAt}" /></td>
              <td class="text-end">
                <a class="btn btn-outline-dark btn-sm action-btn" href="${pageContext.request.contextPath}/admin/users/${u.id}/edit">
                  <i class="bi bi-pencil-square"></i>
                </a>

                <button class="btn btn-outline-danger btn-sm action-btn" type="button"
                        data-bs-toggle="modal" data-bs-target="#confirmDeleteModal"
                        data-user-id="${u.id}" data-user-name="<c:out value='${u.fullName}'/>">
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="border-radius: 18px;">
          <div class="modal-header">
            <h5 class="modal-title fw-bold">Xác nhận xoá user</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            Bạn chắc chắn muốn xoá <span class="fw-bold" id="deleteUserName">user</span>?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-dark action-btn" data-bs-dismiss="modal">Huỷ</button>
            <form id="deleteForm" method="post" action="#" class="m-0">
              <button type="submit" class="btn btn-danger action-btn">
                <i class="bi bi-trash me-1"></i> Xoá
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <div class="mt-4 footer-note">
      <div class="d-flex justify-content-between flex-wrap gap-2">
        <span>UI based on provided template.</span>
        <span>Spring MVC + JSP + JdbcTemplate.</span>
      </div>
    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    const modal = document.getElementById('confirmDeleteModal');
    modal.addEventListener('show.bs.modal', (event) => {
      const btn = event.relatedTarget;
      const id = btn.getAttribute('data-user-id');
      const name = btn.getAttribute('data-user-name');

      document.getElementById('deleteUserName').textContent = name || 'user';
      document.getElementById('deleteForm').action = '${pageContext.request.contextPath}' + '/admin/users/' + id + '/delete';
    });
  </script>
</body>
</html>
