<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Đăng ký – Uniqlo</title>
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
        }

        body {
            font-family: 'Inter', sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, var(--bg-from) 0%, var(--bg-to) 50%, #0f3460 100%);
            padding: 24px;
            overflow: hidden;
            position: relative;
        }

        body::before {
            content: '';
            position: fixed;
            width: 600px; height: 600px;
            border-radius: 50%;
            background: radial-gradient(circle, rgba(108,99,255,0.25), transparent);
            top: -200px; right: -200px;
            animation: blob 10s ease-in-out infinite alternate;
            pointer-events: none;
        }
        body::after {
            content: '';
            position: fixed;
            width: 400px; height: 400px;
            border-radius: 50%;
            background: radial-gradient(circle, rgba(255,101,132,0.25), transparent);
            bottom: -100px; left: -100px;
            animation: blob 7s ease-in-out infinite alternate-reverse;
            pointer-events: none;
        }
        @keyframes blob {
            from { transform: scale(1);   filter: blur(60px); }
            to   { transform: scale(1.3); filter: blur(80px); }
        }

        .card {
            width: 100%;
            max-width: 520px;
            background: var(--glass);
            backdrop-filter: blur(20px);
            border: 1px solid var(--glass-b);
            border-radius: 24px;
            padding: 48px 44px;
            box-shadow: 0 30px 70px rgba(0,0,0,0.5);
            position: relative;
            z-index: 1;
            animation: fadeUp 0.5s ease;
        }
        @keyframes fadeUp {
            from { opacity: 0; transform: translateY(20px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        /* Progress steps */
        .steps {
            display: flex;
            align-items: center;
            margin-bottom: 32px;
            gap: 0;
        }
        .step {
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .step-dot {
            width: 28px; height: 28px;
            border-radius: 50%;
            background: rgba(255,255,255,0.1);
            border: 2px solid rgba(255,255,255,0.15);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 0.7rem;
            font-weight: 700;
            color: var(--text-muted);
            transition: all 0.3s;
        }
        .step.active .step-dot {
            background: linear-gradient(135deg, var(--primary), var(--accent));
            border-color: transparent;
            color: #fff;
        }
        .step-label { font-size: 0.75rem; color: var(--text-muted); font-weight: 500; }
        .step.active .step-label { color: var(--text); }
        .step-line {
            flex: 1;
            height: 2px;
            background: rgba(255,255,255,0.1);
            margin: 0 8px;
        }

        h1 {
            font-size: 1.7rem;
            font-weight: 700;
            color: var(--text);
            margin-bottom: 6px;
        }
        .subtitle {
            font-size: 0.875rem;
            color: var(--text-muted);
            margin-bottom: 28px;
        }
        .subtitle a { color: var(--primary); text-decoration: none; font-weight: 500; }
        .subtitle a:hover { text-decoration: underline; }

        .alert {
            padding: 12px 16px;
            border-radius: var(--radius);
            margin-bottom: 20px;
            font-size: 0.875rem;
            font-weight: 500;
            animation: slideDown 0.3s ease;
        }
        @keyframes slideDown { from { opacity:0; transform:translateY(-8px); } to { opacity:1; transform:translateY(0); } }
        .alert-error { background: rgba(255,101,132,0.15); border: 1px solid rgba(255,101,132,0.4); color: #ff9aae; }

        /* Two-column grid */
        .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
        .form-grid .full { grid-column: 1 / -1; }

        .form-group { margin-bottom: 0; }
        .form-group label {
            display: block;
            font-size: 0.75rem;
            font-weight: 600;
            color: var(--text-muted);
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 7px;
        }
        .input-wrapper { position: relative; display: flex; align-items: center; }
        .input-icon {
            position: absolute;
            left: 12px;
            color: var(--text-muted);
            font-size: 0.95rem;
            pointer-events: none;
            width: 18px;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
        .form-control {
            width: 100%;
            padding: 12px 12px 12px 38px;
            background: var(--input-bg);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: var(--radius);
            color: var(--text);
            font-size: 0.9rem;
            font-family: 'Inter', sans-serif;
            transition: border-color 0.25s, box-shadow 0.25s;
            outline: none;
        }
        .form-control::placeholder { color: rgba(255,255,255,0.25); }
        .form-control:focus {
            border-color: var(--primary);
            background: rgba(108,99,255,0.08);
            box-shadow: 0 0 0 3px rgba(108,99,255,0.18);
        }
        .form-control:focus ~ .input-icon { color: var(--primary); }
        select.form-control { padding-left: 38px; cursor: pointer; }
        select.form-control option { background: #1a1a2e; color: #e8e8f0; }

        .toggle-pw {
            position: absolute;
            right: 12px;
            background: none;
            border: none;
            cursor: pointer;
            color: var(--text-muted);
            font-size: 1rem;
            padding: 0;
            transition: color 0.2s;
        }
        .toggle-pw:hover { color: var(--primary); }

        /* Password strength */
        .pw-strength { margin-top: 6px; display: flex; gap: 4px; }
        .pw-bar {
            height: 3px;
            flex: 1;
            border-radius: 2px;
            background: rgba(255,255,255,0.1);
            transition: background 0.3s;
        }
        .pw-bar.weak   { background: #ff6584; }
        .pw-bar.medium { background: #ffcc00; }
        .pw-bar.strong { background: #50e896; }
        .pw-label { font-size: 0.72rem; color: var(--text-muted); margin-top: 4px; }

        /* Validation feedback */
        .field-feedback {
            font-size: 0.72rem;
            margin-top: 5px;
            min-height: 14px;
            transition: color 0.2s;
        }
        .field-feedback.ok  { color: #50e896; }
        .field-feedback.err { color: #ff9aae; }

        /* Terms */
        .terms-row {
            display: flex;
            align-items: flex-start;
            gap: 10px;
            margin: 20px 0;
        }
        .custom-checkbox {
            width: 18px; height: 18px; min-width: 18px;
            border: 2px solid rgba(255,255,255,0.25);
            border-radius: 5px;
            background: var(--input-bg);
            appearance: none;
            cursor: pointer;
            position: relative;
            transition: all 0.2s;
            margin-top: 1px;
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
        .terms-text { font-size: 0.8rem; color: var(--text-muted); line-height: 1.5; }
        .terms-text a { color: var(--primary); text-decoration: none; }

        /* Submit */
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
        .btn-primary:disabled { opacity: 0.5; cursor: not-allowed; transform: none; }

        .form-spacer { margin-bottom: 16px; }
    </style>
</head>
<body>
<div class="card">
    <!-- Progress indicator -->
    <div class="steps">
        <div class="step active">
            <div class="step-dot">1</div>
            <div class="step-label">Thông tin</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-dot">2</div>
            <div class="step-label">Xác nhận</div>
        </div>
        <div class="step-line"></div>
        <div class="step">
            <div class="step-dot">3</div>
            <div class="step-label">Hoàn tất</div>
        </div>
    </div>

    <h1>Tạo tài khoản</h1>
    <p class="subtitle">Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>

    <c:if test="${not empty errorMsg}">
        <div class="alert alert-error"><i class="fa-solid fa-triangle-exclamation"></i> ${errorMsg}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/register" id="regForm" novalidate>
        <div class="form-grid">
            <!-- Full name -->
            <div class="form-group full">
                <label for="fullName">Họ và tên</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-regular fa-user"></i></span>
                    <input type="text" id="fullName" name="fullName" class="form-control"
                           placeholder="Nguyễn Văn A"
                           value="${not empty fullName ? fullName : ''}" required/>
                </div>
                <div class="field-feedback" id="fb-name"></div>
            </div>

            <!-- Email -->
            <div class="form-group full">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-regular fa-envelope"></i></span>
                    <input type="email" id="email" name="email" class="form-control"
                           placeholder="example@email.com"
                           value="${not empty email ? email : ''}" required/>
                </div>
                <div class="field-feedback" id="fb-email"></div>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-solid fa-lock"></i></span>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="Tối thiểu 6 ký tự" required/>
                    <button type="button" class="toggle-pw" onclick="togglePw('password')" aria-label="Hiện/ẩn mật khẩu">
                        <i class="fa-regular fa-eye"></i>
                    </button>
                </div>
                <div class="pw-strength">
                    <div class="pw-bar" id="bar1"></div>
                    <div class="pw-bar" id="bar2"></div>
                    <div class="pw-bar" id="bar3"></div>
                </div>
                <div class="pw-label" id="pw-label">Độ mạnh mật khẩu</div>
            </div>

            <!-- Confirm Password -->
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-solid fa-key"></i></span>
                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control"
                           placeholder="Nhập lại mật khẩu" required/>
                    <button type="button" class="toggle-pw" onclick="togglePw('confirmPassword')" aria-label="Hiện/ẩn xác nhận mật khẩu">
                        <i class="fa-regular fa-eye"></i>
                    </button>
                </div>
                <div class="field-feedback" id="fb-confirm"></div>
            </div>

            <!-- Gender -->
            <div class="form-group full">
                <label for="gender">Giới tính</label>
                <div class="input-wrapper">
                    <span class="input-icon"><i class="fa-solid fa-venus-mars"></i></span>
                    <select id="gender" name="gender" class="form-control">
                        <option value="">-- Chọn giới tính --</option>
                        <option value="Male"            ${gender eq 'Male'            ? 'selected' : ''}>Nam</option>
                        <option value="Female"          ${gender eq 'Female'          ? 'selected' : ''}>Nữ</option>
                        <option value="Decline to state"${gender eq 'Decline to state'? 'selected' : ''}>Không muốn tiết lộ</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-spacer"></div>

        <!-- Terms -->
        <div class="terms-row">
            <input type="checkbox" id="agreeTerms" class="custom-checkbox" required/>
            <label class="terms-text" for="agreeTerms">
                Tôi đồng ý với <a href="#">Điều khoản dịch vụ</a> và
                <a href="#">Chính sách bảo mật</a> của Uniqlo
            </label>
        </div>

        <button type="submit" class="btn-primary" id="regBtn">Tạo tài khoản <i class="fa-solid fa-arrow-right"></i></button>
    </form>
</div>

<script>
    function togglePw(id) {
        const el = document.getElementById(id);
        el.type = el.type === 'password' ? 'text' : 'password';

        // toggle icon
        const btn = el.parentElement.querySelector('.toggle-pw i');
        if (btn) btn.className = (el.type === 'password') ? 'fa-regular fa-eye' : 'fa-regular fa-eye-slash';
    }

    // Password strength
    document.getElementById('password').addEventListener('input', function() {
        const v = this.value;
        const bars = [document.getElementById('bar1'),
                      document.getElementById('bar2'),
                      document.getElementById('bar3')];
        const label = document.getElementById('pw-label');
        bars.forEach(b => { b.className = 'pw-bar'; });

        if (v.length === 0) { label.textContent = 'Độ mạnh mật khẩu'; return; }
        if (v.length < 4)  { bars[0].classList.add('weak');   label.textContent = 'Yếu'; return; }
        if (v.length < 8)  {
            bars[0].classList.add('medium'); bars[1].classList.add('medium');
            label.textContent = 'Trung bình'; return;
        }
        const strong = /[A-Z]/.test(v) && /[0-9]/.test(v) && /[^A-Za-z0-9]/.test(v);
        if (strong) {
            bars.forEach(b => b.classList.add('strong'));
            label.textContent = 'Mạnh';
        } else {
            bars[0].classList.add('medium'); bars[1].classList.add('medium');
            label.textContent = 'Khá tốt';
        }
        checkConfirm();
    });

    function checkConfirm() {
        const pw  = document.getElementById('password').value;
        const cpw = document.getElementById('confirmPassword').value;
        const fb  = document.getElementById('fb-confirm');
        if (!cpw) { fb.textContent = ''; return; }
        if (pw === cpw) { fb.textContent = 'Mật khẩu khớp'; fb.className='field-feedback ok'; }
        else            { fb.textContent = 'Mật khẩu không khớp'; fb.className='field-feedback err'; }
    }

    // Full name feedback
    document.getElementById('fullName').addEventListener('input', function() {
        const fb = document.getElementById('fb-name');
        if (this.value.trim().length < 2) { fb.textContent = 'Họ tên quá ngắn'; fb.className='field-feedback err'; }
        else { fb.textContent = 'Hợp lệ'; fb.className='field-feedback ok'; }
    });

    // Email live check
    document.getElementById('email').addEventListener('input', function() {
        const fb = document.getElementById('fb-email');
        const valid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.value);
        if (!this.value) { fb.textContent = ''; return; }
        fb.textContent = valid ? 'Email hợp lệ' : 'Email không hợp lệ';
        fb.className = 'field-feedback ' + (valid ? 'ok' : 'err');
    });

    document.getElementById('regForm').addEventListener('submit', function(e) {
        if (!document.getElementById('agreeTerms').checked) {
            e.preventDefault();
            alert('Bạn cần đồng ý với điều khoản dịch vụ!');
            return;
        }
        const pw  = document.getElementById('password').value;
        const cpw = document.getElementById('confirmPassword').value;
        if (pw !== cpw) {
            e.preventDefault();
            alert('Mật khẩu xác nhận không khớp!');
            return;
        }
        const btn = document.getElementById('regBtn');
        btn.textContent = 'Đang xử lý...';
        btn.disabled = true;
    });
</script>
</body>
</html>
