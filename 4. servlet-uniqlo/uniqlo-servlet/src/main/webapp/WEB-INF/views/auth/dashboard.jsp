<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dashboard – Uniqlo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet"/>
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        :root {
            --primary:   #6c63ff;
            --accent:    #ff6584;
            --success:   #50e896;
            --warning:   #ffcc00;
            --bg:        #0e0e1a;
            --surface:   #16162a;
            --surface-2: #1e1e35;
            --glass:     rgba(255,255,255,0.05);
            --glass-b:   rgba(255,255,255,0.1);
            --text:      #e8e8f0;
            --text-muted:#8888aa;
            --radius:    14px;
        }

        body {
            font-family: 'Inter', sans-serif;
            background: var(--bg);
            color: var(--text);
            min-height: 100vh;
        }

        /* ── TOP NAVBAR ── */
        .navbar {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 32px;
            height: 64px;
            background: var(--surface);
            border-bottom: 1px solid var(--glass-b);
            position: sticky;
            top: 0;
            z-index: 100;
            backdrop-filter: blur(10px);
        }
        .navbar-brand {
            font-size: 1.3rem;
            font-weight: 800;
            letter-spacing: 3px;
            background: linear-gradient(135deg, var(--primary), var(--accent));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }
        .navbar-right { display: flex; align-items: center; gap: 16px; }
        .avatar-chip {
            display: flex;
            align-items: center;
            gap: 10px;
            padding: 6px 14px 6px 6px;
            background: var(--glass);
            border: 1px solid var(--glass-b);
            border-radius: 100px;
            cursor: pointer;
        }
        .avatar-img {
            width: 34px; height: 34px;
            border-radius: 50%;
            object-fit: cover;
            background: linear-gradient(135deg, var(--primary), var(--accent));
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1rem;
            font-weight: 700;
            color: #fff;
        }
        .avatar-name { font-size: 0.875rem; font-weight: 600; }
        .badge-role {
            font-size: 0.65rem;
            font-weight: 700;
            padding: 2px 8px;
            border-radius: 100px;
            letter-spacing: 0.5px;
            text-transform: uppercase;
        }
        .badge-admin  { background: rgba(108,99,255,0.25); color: #a99eff; border: 1px solid rgba(108,99,255,0.4); }
        .badge-user   { background: rgba(80,232,150,0.15); color: #50e896; border: 1px solid rgba(80,232,150,0.3); }
        .btn-logout {
            padding: 8px 18px;
            background: linear-gradient(135deg, var(--accent), #ff4466);
            border: none;
            border-radius: 100px;
            color: #fff;
            font-size: 0.85rem;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: opacity 0.2s, transform 0.15s;
        }
        .btn-logout:hover { opacity: 0.85; transform: translateY(-1px); }

        /* ── MAIN LAYOUT ── */
        .main {
            max-width: 1200px;
            margin: 0 auto;
            padding: 36px 24px;
        }

        /* ── HERO GREETING ── */
        .hero {
            background: linear-gradient(135deg,
                rgba(108,99,255,0.2) 0%,
                rgba(255,101,132,0.1) 100%);
            border: 1px solid rgba(108,99,255,0.25);
            border-radius: 20px;
            padding: 40px 44px;
            margin-bottom: 28px;
            position: relative;
            overflow: hidden;
            animation: fadeUp 0.5s ease;
        }
        .hero::before {
            content: 'UNIQLO';
            position: absolute;
            right: -20px; top: 50%;
            transform: translateY(-50%);
            font-size: 8rem;
            font-weight: 900;
            letter-spacing: 8px;
            color: rgba(255,255,255,0.03);
            pointer-events: none;
        }
        @keyframes fadeUp {
            from { opacity:0; transform:translateY(16px); }
            to   { opacity:1; transform:translateY(0); }
        }
        .hero-greeting {
            font-size: 0.85rem;
            color: var(--text-muted);
            font-weight: 500;
            margin-bottom: 6px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        .hero-name {
            font-size: 2rem;
            font-weight: 800;
            margin-bottom: 10px;
        }
        .hero-name span {
            background: linear-gradient(135deg, var(--primary), var(--accent));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }
        .hero-meta {
            font-size: 0.85rem;
            color: var(--text-muted);
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }
        .hero-meta span { display: flex; align-items: center; gap: 6px; }

        /* ── STATS ROW ── */
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 16px;
            margin-bottom: 28px;
            animation: fadeUp 0.6s ease 0.1s both;
        }
        .stat-card {
            background: var(--surface);
            border: 1px solid var(--glass-b);
            border-radius: var(--radius);
            padding: 22px;
            position: relative;
            overflow: hidden;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .stat-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 12px 30px rgba(0,0,0,0.3);
        }
        .stat-card::before {
            content: '';
            position: absolute;
            top: 0; left: 0; right: 0;
            height: 3px;
        }
        .stat-card.purple::before { background: linear-gradient(90deg, var(--primary), #a99eff); }
        .stat-card.pink::before   { background: linear-gradient(90deg, var(--accent), #ff9aae); }
        .stat-card.green::before  { background: linear-gradient(90deg, var(--success), #00ffaa); }
        .stat-card.yellow::before { background: linear-gradient(90deg, var(--warning), #ffaa00); }
        .stat-icon { font-size: 1.8rem; margin-bottom: 10px; }
        .stat-value {
            font-size: 1.8rem;
            font-weight: 800;
            color: var(--text);
            line-height: 1;
        }
        .stat-label {
            font-size: 0.78rem;
            color: var(--text-muted);
            margin-top: 4px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        /* ── CONTENT GRID ── */
        .content-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            animation: fadeUp 0.7s ease 0.2s both;
        }
        .card {
            background: var(--surface);
            border: 1px solid var(--glass-b);
            border-radius: var(--radius);
            overflow: hidden;
        }
        .card-header {
            padding: 18px 22px;
            border-bottom: 1px solid var(--glass-b);
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .card-header-icon {
            width: 34px; height: 34px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1rem;
        }
        .card-header-icon.purple { background: rgba(108,99,255,0.2); }
        .card-header-icon.pink   { background: rgba(255,101,132,0.2); }
        .card-header-icon.green  { background: rgba(80,232,150,0.15); }
        .card-header-icon.yellow { background: rgba(255,204,0,0.15); }
        .card-title {
            font-size: 0.9rem;
            font-weight: 700;
            color: var(--text);
        }
        .card-body { padding: 22px; }

        /* Info table */
        .info-table { width: 100%; border-collapse: collapse; }
        .info-table tr { border-bottom: 1px solid rgba(255,255,255,0.05); }
        .info-table tr:last-child { border-bottom: none; }
        .info-table td {
            padding: 10px 0;
            font-size: 0.85rem;
            vertical-align: top;
        }
        .info-table td:first-child {
            color: var(--text-muted);
            font-weight: 500;
            width: 45%;
            padding-right: 12px;
        }
        .info-table td:last-child { color: var(--text); word-break: break-all; }
        .mono { font-family: 'Courier New', monospace; font-size: 0.8rem; color: #a99eff; }

        /* Cookie tags */
        .cookie-list { display: flex; flex-direction: column; gap: 8px; }
        .cookie-item {
            background: var(--surface-2);
            border: 1px solid rgba(255,255,255,0.06);
            border-radius: 8px;
            padding: 10px 14px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .cookie-name { font-size: 0.82rem; font-weight: 600; color: #a99eff; font-family: monospace; }
        .cookie-val  { font-size: 0.78rem; color: var(--text-muted); max-width: 55%; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }

        /* Visit counter big number */
        .visit-counter {
            text-align: center;
            padding: 20px 0;
        }
        .visit-number {
            font-size: 4rem;
            font-weight: 900;
            background: linear-gradient(135deg, var(--primary), var(--accent));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            line-height: 1;
            animation: countUp 0.8s ease;
        }
        @keyframes countUp {
            from { opacity:0; transform:scale(0.8); }
            to   { opacity:1; transform:scale(1); }
        }
        .visit-sub { font-size: 0.8rem; color: var(--text-muted); margin-top: 8px; }

        /* Quick actions */
        .action-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; }
        .action-btn {
            padding: 14px;
            background: var(--surface-2);
            border: 1px solid rgba(255,255,255,0.08);
            border-radius: 10px;
            color: var(--text);
            text-decoration: none;
            font-size: 0.85rem;
            font-weight: 600;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 6px;
            transition: all 0.2s;
            cursor: pointer;
        }
        .action-btn:hover {
            background: rgba(108,99,255,0.15);
            border-color: rgba(108,99,255,0.3);
            transform: translateY(-2px);
        }
        .action-btn .icon { font-size: 1.5rem; }

        /* Session timeline */
        .timeline { position: relative; padding-left: 20px; }
        .timeline::before {
            content: '';
            position: absolute;
            left: 6px; top: 6px; bottom: 6px;
            width: 2px;
            background: rgba(255,255,255,0.08);
        }
        .tl-item { position: relative; margin-bottom: 16px; }
        .tl-item:last-child { margin-bottom: 0; }
        .tl-dot {
            position: absolute;
            left: -17px; top: 3px;
            width: 10px; height: 10px;
            border-radius: 50%;
            border: 2px solid;
        }
        .tl-dot.create  { background: var(--primary); border-color: var(--primary); }
        .tl-dot.access  { background: var(--success);  border-color: var(--success); }
        .tl-dot.timeout { background: var(--warning); border-color: var(--warning); }
        .tl-label  { font-size: 0.78rem; color: var(--text-muted); margin-bottom: 2px; }
        .tl-value  { font-size: 0.85rem; color: var(--text); font-weight: 500; }

        /* Responsive */
        @media (max-width: 768px) {
            .stats-grid    { grid-template-columns: repeat(2,1fr); }
            .content-grid  { grid-template-columns: 1fr; }
            .hero          { padding: 28px 24px; }
            .hero-name     { font-size: 1.5rem; }
        }
        @media (max-width: 480px) {
            .stats-grid { grid-template-columns: 1fr 1fr; }
            .navbar { padding: 0 16px; }
        }
    </style>
</head>
<body>

<!-- ── NAVBAR ── -->
<nav class="navbar">
    <div class="navbar-brand">UNIQLO</div>
    <div class="navbar-right">
        <div class="avatar-chip">
            <div class="avatar-img">
                <c:choose>
                    <c:when test="${not empty user.avatar}">
                        <img src="${user.avatar}" alt="avatar" style="width:100%;height:100%;border-radius:50%;object-fit:cover;"/>
                    </c:when>
                    <c:otherwise>${avatarInitial}</c:otherwise>
                </c:choose>
            </div>
            <span class="avatar-name">${not empty user.fullName ? user.fullName : user.email}</span>
            <span class="badge-role ${user.role eq 'ADMIN' ? 'badge-admin' : 'badge-user'}">${user.role}</span>
        </div>
        <a href="${pageContext.request.contextPath}/logout" class="btn-logout">🚪 Đăng xuất</a>
    </div>
</nav>

<!-- ── MAIN ── -->
<main class="main">

    <!-- Hero greeting -->
    <div class="hero">
        <div class="hero-greeting">Chào mừng trở lại 👋</div>
        <div class="hero-name">Xin chào, <span>${not empty user.fullName ? user.fullName : 'Người dùng'}</span>!</div>
        <div class="hero-meta">
            <span>📧 ${user.email}</span>
            <span>⚡ ${user.role}</span>
            <c:if test="${not empty user.gender}"><span>👤 ${user.gender}</span></c:if>
            <c:if test="${not empty createdAtStr}">
                <span>📅 Tham gia: ${createdAtStr}</span>
            </c:if>
        </div>
    </div>

    <!-- Stats row -->
    <div class="stats-grid">
        <div class="stat-card purple">
            <div class="stat-icon">🌐</div>
            <div class="stat-value">${visitCount}</div>
            <div class="stat-label">Lượt truy cập</div>
        </div>
        <div class="stat-card pink">
            <div class="stat-icon">⏱</div>
            <div class="stat-value">${sessionTimeout}<span style="font-size:1rem;font-weight:400"> ph</span></div>
            <div class="stat-label">Session timeout</div>
        </div>
        <div class="stat-card green">
            <div class="stat-icon">🍪</div>
            <div class="stat-value">
                <c:choose>
                    <c:when test="${empty cookies}">0</c:when>
                    <c:otherwise>${fn:length(cookies)}</c:otherwise>
                </c:choose>
            </div>
            <div class="stat-label">Cookies hiện tại</div>
        </div>
        <div class="stat-card yellow">
            <div class="stat-icon">🔑</div>
            <div class="stat-value" style="font-size:1rem;word-break:break-all;font-weight:600">
                ${not empty sessionId ? sessionId.substring(0, 8) : '–'}...
            </div>
            <div class="stat-label">Session ID</div>
        </div>
    </div>

    <!-- Content grid -->
    <div class="content-grid">

        <!-- SESSION INFO -->
        <div class="card">
            <div class="card-header">
                <div class="card-header-icon purple">🖥</div>
                <div class="card-title">Thông tin Session</div>
            </div>
            <div class="card-body">
                <div class="timeline">
                    <div class="tl-item">
                        <div class="tl-dot create"></div>
                        <div class="tl-label">Session tạo lúc</div>
                        <div class="tl-value">${sessionCreated}</div>
                    </div>
                    <div class="tl-item">
                        <div class="tl-dot access"></div>
                        <div class="tl-label">Truy cập gần nhất</div>
                        <div class="tl-value">${sessionAccessed}</div>
                    </div>
                    <div class="tl-item">
                        <div class="tl-dot timeout"></div>
                        <div class="tl-label">Timeout sau</div>
                        <div class="tl-value">${sessionTimeout} phút không hoạt động</div>
                    </div>
                </div>
                <table class="info-table" style="margin-top:16px">
                    <tr>
                        <td>Session ID đầy đủ</td>
                        <td class="mono">${sessionId}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td><span class="badge-role ${user.role eq 'ADMIN' ? 'badge-admin' : 'badge-user'}">${user.role}</span></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- COOKIES -->
        <div class="card">
            <div class="card-header">
                <div class="card-header-icon yellow">🍪</div>
                <div class="card-title">Browser Cookies</div>
            </div>
            <div class="card-body">
                <div class="cookie-list">
                    <c:choose>
                        <c:when test="${empty cookies}">
                            <p style="color:var(--text-muted);font-size:0.875rem;text-align:center;padding:20px 0">
                                Không có cookie nào
                            </p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="ck" items="${cookies}">
                                <div class="cookie-item">
                                    <span class="cookie-name">${ck.name}</span>
                                    <span class="cookie-val" title="${ck.value}">
                                        <c:choose>
                                            <c:when test="${ck.name eq 'remember_token'}">••••••••••••</c:when>
                                            <c:otherwise>${ck.value}</c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- VISIT COUNTER -->
        <div class="card">
            <div class="card-header">
                <div class="card-header-icon pink">📊</div>
                <div class="card-title">Lượt truy cập Website</div>
            </div>
            <div class="card-body">
                <div class="visit-counter">
                    <div class="visit-number" id="visit-display">0</div>
                    <div class="visit-sub">Tổng lượt truy cập từ khi server khởi động<br/>
                        (Được đếm bởi <code style="color:#a99eff">VisitCountFilter</code> – XML config)</div>
                </div>
                <table class="info-table" style="margin-top:12px">
                    <tr>
                        <td>Filter config</td>
                        <td class="mono">web.xml (XML)</td>
                    </tr>
                    <tr>
                        <td>Exclude prefix</td>
                        <td class="mono">/assets</td>
                    </tr>
                    <tr>
                        <td>Lưu trữ</td>
                        <td>MySQL + ServletContext</td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- QUICK ACTIONS -->
        <div class="card">
            <div class="card-header">
                <div class="card-header-icon green">⚡</div>
                <div class="card-title">Điều hướng nhanh</div>
            </div>
            <div class="card-body">
                <div class="action-grid">
                    <a href="${pageContext.request.contextPath}/productions" class="action-btn">
                        <span class="icon">📦</span>
                        <span>Sản phẩm</span>
                    </a>
                    <a href="${pageContext.request.contextPath}/logout" class="action-btn">
                        <span class="icon">🚪</span>
                        <span>Đăng xuất</span>
                    </a>
                    <div class="action-btn" onclick="location.reload()">
                        <span class="icon">🔄</span>
                        <span>Làm mới</span>
                    </div>
                    <div class="action-btn" onclick="showInfo()">
                        <span class="icon">ℹ️</span>
                        <span>Về app</span>
                    </div>
                </div>

                <div style="margin-top:20px;padding:14px;background:var(--surface-2);border-radius:10px;font-size:0.78rem;color:var(--text-muted);line-height:1.7">
                    <div style="font-weight:700;color:var(--text);margin-bottom:6px">🎓 Kỹ thuật đã áp dụng</div>
                    <div>✅ <strong>Cookie</strong> – Remember Me 7 ngày</div>
                    <div>✅ <strong>Session</strong> – Quản lý phiên đăng nhập</div>
                    <div>✅ <strong>AuthFilter</strong> – Annotation config</div>
                    <div>✅ <strong>VisitCountFilter</strong> – XML config + FilterConfig</div>
                    <div>✅ <strong>EncodingFilter</strong> – UTF-8 annotation</div>
                    <div>✅ <strong>JSP Cookie/Session</strong> – Hiển thị ở dashboard</div>
                </div>
            </div>
        </div>

    </div><!-- /content-grid -->
</main>

<script>
    // Animated counter for visit count
    const target = parseInt('${visitCount}') || 0;
    const el = document.getElementById('visit-display');
    let current = 0;
    const step = Math.max(1, Math.floor(target / 60));
    const timer = setInterval(() => {
        current = Math.min(current + step, target);
        el.textContent = current.toLocaleString('vi-VN');
        if (current >= target) { clearInterval(timer); el.textContent = target.toLocaleString('vi-VN'); }
    }, 16);

    function showInfo() {
        alert('Uniqlo Servlet Demo\nJakarta EE 10 | Java 17 | Tomcat 10\n\nServlet • Filter • Session • Cookie • JSP');
    }
</script>
</body>
</html>
