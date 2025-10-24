<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HRMS - Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8fafc; }
        .navbar-brand { font-weight: 700; letter-spacing: .5px; }
        .card-hover:hover { transform: translateY(-4px); box-shadow: 0 1rem 1.5rem rgba(0,0,0,.08); }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/">HRMS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav" aria-controls="nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="employees">Nhân viên</a></li>
                <li class="nav-item"><a class="nav-link" href="grades">Bậc lương</a></li>
            </ul>
        </div>
    </div>
</nav>

<header class="py-5 bg-light">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-7">
                <h1 class="display-5 fw-bold">Hệ thống quản lý nhân sự</h1>
                <p class="lead">Quản lý hồ sơ, theo dõi quá trình công tác, khen thưởng, kỷ luật, bậc lương và chấm công.</p>
            </div>
            <div class="col-md-5 text-center">
                <img class="img-fluid" src="https://images.unsplash.com/photo-1529101091764-c3526daf38fe?q=80&w=800&auto=format&fit=crop" alt="HR Illustration">
            </div>
        </div>
    </div>
</header>

<main class="container my-5">
    <div class="row g-4">
        <div class="col-md-6">
            <div class="card card-hover">
                <div class="card-body p-4">
                    <h5 class="card-title">Quản lý nhân viên</h5>
                    <p class="card-text">Thêm, sửa, xoá và tìm kiếm hồ sơ nhân viên theo nhiều tiêu chí.</p>
                    <a href="employees" class="btn btn-primary">Vào trang nhân viên</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card card-hover">
                <div class="card-body p-4">
                    <h5 class="card-title">Bậc lương</h5>
                    <p class="card-text">Quản lý khung lương theo bậc, giới hạn min/max và thăng hạng.</p>
                    <a href="grades" class="btn btn-outline-primary">Vào trang bậc lương</a>
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="py-4 bg-white border-top">
    <div class="container text-center text-secondary">© 2025 HRMS Demo</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
