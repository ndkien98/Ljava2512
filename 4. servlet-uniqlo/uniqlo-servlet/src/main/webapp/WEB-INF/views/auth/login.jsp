<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Đăng nhập – Uniqlo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" referrerpolicy="no-referrer"/>
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        :root {
            --primary:    #6c63ff;
            --primary-d:  #574fd6;
            --accent:     #ff6584;
            --bg-from:    #1a1a2e;
            --bg-to:      #16213e;
            --glass:      rgba(255,255,255,0.06);
            --glass-b:    rgba(255,255,255,0.12);
            --text:       #e8e8f0;
            --text-muted: #9999bb;
            --input-bg:   rgba(255,255,255,0.08);
            --radius:     14px;
            --shadow:     0 25px 60px rgba(0,0,0,0.5);
        }

        body {
            font-family: 'Inter', sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, var(--bg-from) 0%, var(--bg-to) 50%, #0f3460 100%);
            overflow: hidden;
            position: relative;
        }

        /* Animated background blobs */
        body::before, body::after {
            content: '';
            position: fixed;
            border-radius: 50%;
            filter: blur(80px);
            opacity: 0.35;
            animation: blob 8s ease-in-out infinite alternate;
        }
        body::before {
            width: 500px; height: 500px;
            background: radial-gradient(circle, #6c63ff, transparent);
            top: -150px; left: -150px;
        }
        body::after {
            width: 400px; height: 400px;
            background: radial-gradient(circle, #ff6584, transparent);
            bottom: -100px; right: -100px;
            animation-delay: -4s;
        }
        @keyframes blob {
            0%   { transform: scale(1) translate(0,0); }
            100% { transform: scale(1.2) translate(30px,20px); }
        }

        .page-wrapper {
            display: flex;
            width: 900px;
            max-width: 95vw;
            min-height: 560px;
            border-radius: 24px;
            overflow: hidden;
            box-shadow: var(--shadow);
            background: var(--glass);
            backdrop-filter: blur(20px);
            border: 1px solid var(--glass-b);
            position: relative;
            z-index: 1;
        }

        /* Left panel */
        .panel-left {
            flex: 1;
            background: linear-gradient(160deg, rgba(108,99,255,0.8), rgba(255,101,132,0.6));
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 40px;
            text-align: center;
        }
        .brand-logo {
            font-size: 2.5rem;
            font-weight: 800;
            letter-spacing: 4px;
            color: #fff;
            text-transform: uppercase;
            margin-bottom: 16px;
        }
        .brand-tagline {
            font-size: 0.95rem;
            color: rgba(255,255,255,0.8);
            line-height: 1.6;
            max-width: 220px;
        }
        .decorative-circles {
            margin-top: 40px;
            position: relative;
            width: 160px;
            height: 160px;
        }
        .circle {
            position: absolute;
            border-radius: 50%;
            border: 2px solid rgba(255,255,255,0.3);
        }
        .circle-1 { width:160px; height:160px; top:0; left:0; animation: spin 12s linear infinite; }
        .circle-2 { width:110px; height:110px; top:25px; left:25px; animation: spin 8s linear infinite reverse; }
        .circle-3 { width:60px; height:60px; top:50px; left:50px; background: rgba(255,255,255,0.15); }
        @keyframes spin { to { transform: rotate(360deg); } }

        /* Right panel – form */
        .panel-right {
            flex: 1.1;
            padding: 50px 48px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        h1 {
            font-size: 1.8rem;
            font-weight: 700;
            color: var(--text);
            margin-bottom: 6px;
        }
        .subtitle {
            font-size: 0.9rem;
            color: var(--text-muted);
            margin-bottom: 32px;
        }
        .subtitle a { color: var(--primary); text-decoration: none; font-weight: 500; }
        .subtitle a:hover { text-decoration: underline; }

        /* Alerts */
        .alert {
            padding: 12px 16px;
            border-radius: var(--radius);
            margin-bottom: 20px;
            font-size: 0.875rem;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 8px;
            animation: slideDown 0.3s ease;
        }
        @keyframes slideDown { from { opacity:0; transform:translateY(-8px); } to { opacity:1; transform:translateY(0); } }
        .alert-error   { background: rgba(255,101,132,0.15); border: 1px solid rgba(255,101,132,0.4); color: #ff9aae; }
        .alert-success { background: rgba(80,200,120,0.15); border: 1px solid rgba(80,200,120,0.4); color: #6efcb0; }

        /* Form group */
        .form-group { margin-bottom: 20px; position: relative; }
        .form-group label {
            display: block;
            font-size: 0.8rem;
            font-weight: 600;
            color: var(--text-muted);
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 8px;
        }
        .input-wrapper {
            position: relative;
            display: flex;
            align-items: center;
        }
        .input-icon {
            position: absolute;
            left: 14px;
            color: var(--text-muted);
            font-size: 1rem;
            pointer-events: none;
            transition: color 0.2s;
            width: 18px;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
        .form-control {
            width: 100%;
            padding: 13px 14px 13px 42px;
            background: var(--input-bg);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: var(--radius);
            color: var(--text);
            font-size: 0.95rem;
            font-family: 'Inter', sans-serif;
            transition: border-color 0.25s, box-shadow 0.25s, background 0.25s;
            outline: none;
        }
        .form-control::placeholder { color: rgba(255,255,255,0.3); }
        .form-control:focus {
            border-color: var(--primary);
            background: rgba(108,99,255,0.08);
            box-shadow: 0 0 0 3px rgba(108,99,255,0.18);
        }
        .form-control:focus + .focus-bar { width: 100%; }
        .form-control:focus ~ .input-icon { color: var(--primary); }

        /* Toggle password */
        .toggle-pw {
            position: absolute;
            right: 14px;
            background: none;
            border: none;
            cursor: pointer;
            color: var(--text-muted);
            font-size: 1.1rem;
            padding: 0;
            transition: color 0.2s;
        }
        .toggle-pw:hover { color: var(--primary); }

        /* Remember me row */
        .row-extra {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 24px;
        }
        .checkbox-label {
            display: flex;
            align-items: center;
            gap: 8px;
            cursor: pointer;
            color: var(--text-muted);
            font-size: 0.875rem;
            user-select: none;
        }
        .custom-checkbox {
            width: 18px; height: 18px;
            border: 2px solid rgba(255,255,255,0.25);
            border-radius: 5px;
            background: var(--input-bg);
            appearance: none;
            cursor: pointer;
            position: relative;
            transition: all 0.2s;
        }
        .custom-checkbox:checked {
            background: var(--primary);
            border-color: var(--primary);
        }
        .custom-checkbox:checked::after {
            content: '✓';
            position: absolute;
            top: -2px; left: 2px;
            color: #fff;
            font-size: 12px;
            font-weight: 700;
        }
        .forgot-link {
            font-size: 0.875rem;
            color: var(--primary);
            text-decoration: none;
            font-weight: 500;
        }
        .forgot-link:hover { text-decoration: underline; }

        /* Submit button */
        .btn-primary {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, var(--primary), var(--accent));
            border: none;
            border-radius: var(--radius);
            color: #fff;
            font-size: 1rem;
            font-weight: 700;
            font-family: 'Inter', sans-serif;
            cursor: pointer;
            transition: opacity 0.2s, transform 0.15s, box-shadow 0.2s;
            box-shadow: 0 8px 25px rgba(108,99,255,0.35);
            letter-spacing: 0.5px;
        }
        .btn-primary:hover {
            opacity: 0.92;
            transform: translateY(-2px);
            box-shadow: 0 12px 35px rgba(108,99,255,0.45);
        }
        .btn-primary:active { transform: translateY(0); }

        /* Divider */
        .divider {
            display: flex;
            align-items: center;
            gap: 12px;
            margin: 24px 0;
            color: var(--text-muted);
            font-size: 0.8rem;
        }
        .divider::before, .divider::after {
            content: '';
            flex: 1;
            height: 1px;
            background: rgba(255,255,255,0.1);
        }

        /* Social placeholder */
        .social-row { display: flex; gap: 12px; }
        .btn-social {
            flex: 1;
            padding: 11px;
            background: var(--input-bg);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: var(--radius);
            color: var(--text);
            font-size: 0.875rem;
            font-family: 'Inter', sans-serif;
            cursor: pointer;
            transition: background 0.2s, border-color 0.2s;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 6px;
            font-weight: 500;
        }
        .btn-social:hover {
            background: rgba(255,255,255,0.12);
            border-color: rgba(255,255,255,0.2);
        }

        @media (max-width: 680px) {
            .panel-left { display: none; }
            .panel-right { padding: 40px 28px; }
        }
    </style>
</head>
<body>
<div class="page-wrapper">

    <!-- LEFT PANEL -->
    <div class="panel-left">
        <div class="brand-logo">UNIQLO</div>
        <p class="brand-tagline">Chào mừng trở lại!<br/>Đăng nhập để khám phá bộ sưu tập mới nhất.</p>
        <div class="decorative-circles">
            <div class="circle circle-1"></div>
            <div class="circle circle-2"></div>
            <div class="circle circle-3"></div>
        </div>
    </div>

    <!-- RIGHT PANEL – FORM -->
    <div class="panel-right">
        <h1>Đăng nhập</h1>
        <p class="subtitle">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a></p>

        <!-- Error / Success alerts -->
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-error"><i class="fa-solid fa-triangle-exclamation"></i> ${errorMsg}</div>
        </c:if>
        <c:if test="${param.success eq 'registered'}">
            <div class="alert alert-success"><i class="fa-solid fa-circle-check"></i> Đăng ký thành công! Vui lòng đăng nhập.</div>
        </c:if>
        <c:if test="${param.success eq 'loggedout'}">
            <div class="alert alert-success"><i class="fa-solid fa-circle-check"></i> Bạn đã đăng xuất thành công.</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/login" id="loginForm" novalidate>
            <c:if test="${not empty param.redirect}">
                <input type="hidden" name="redirect" value="${param.redirect}"/>
            </c:if>

            <!-- Email -->
            <div class="form-group">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-regular fa-envelope"></i></span>
                    <input type="email" id="email" name="email" class="form-control"
                           placeholder="example@email.com"
                           value="${not empty email ? email : cookie['saved_email'].value}"
                           required/>
                </div>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-solid fa-lock"></i></span>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="Nhập mật khẩu" required/>
                    <button type="button" class="toggle-pw" onclick="togglePw()" title="Hiện/ẩn mật khẩu" aria-label="Hiện/ẩn mật khẩu">
                        <i class="fa-regular fa-eye" id="pwEye"></i>
                    </button>
                </div>
            </div>

            <!-- Remember me & Forgot -->
            <div class="row-extra">
                <label class="checkbox-label">
                    <input type="checkbox" name="rememberMe" class="custom-checkbox"
                           id="rememberMe" ${not empty cookie['saved_email'] ? 'checked' : ''}/>
                    Nhớ đăng nhập
                </label>
                <a href="#" class="forgot-link">Quên mật khẩu?</a>
            </div>

            <button type="submit" class="btn-primary" id="loginBtn">Đăng nhập <i class="fa-solid fa-arrow-right"></i></button>
        </form>

        <div class="divider">hoặc đăng nhập bằng</div>
        <div class="social-row">
            <button type="button" class="btn-social"><i class="fa-brands fa-google"></i> Google</button>
            <button type="button" class="btn-social"><i class="fa-brands fa-facebook"></i> Facebook</button>
        </div>
    </div>
</div>

<script>
    function togglePw() {
        const pw = document.getElementById('password');
        const eye = document.getElementById('pwEye');
        const isHidden = pw.type === 'password';
        pw.type = isHidden ? 'text' : 'password';
        if (eye) eye.className = isHidden ? 'fa-regular fa-eye-slash' : 'fa-regular fa-eye';
    }

    // Client-side validation
    document.getElementById('loginForm').addEventListener('submit', function(e) {
        const email    = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        if (!email || !password) {
            e.preventDefault();
            alert('Vui lòng nhập đầy đủ email và mật khẩu!');
            return;
        }
        const btn = document.getElementById('loginBtn');
        btn.innerHTML = 'Đang đăng nhập...';
        btn.style.opacity = '0.7';
    });
</script>
</body>
</html>
