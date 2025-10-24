<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bậc lương</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
  <div class="row g-4">
    <div class="col-lg-5">
      <div class="card">
        <div class="card-header">Thêm bậc lương</div>
        <div class="card-body">
          <form method="post" action="${pageContext.request.contextPath}/grades/save" class="row g-3">
            <div class="col-12">
              <label class="form-label">Tên bậc</label>
              <input name="name" class="form-control" required>
            </div>
            <div class="col-6">
              <label class="form-label">Lương tối thiểu</label>
              <input name="minSalary" type="number" step="0.01" class="form-control" required>
            </div>
            <div class="col-6">
              <label class="form-label">Lương tối đa</label>
              <input name="maxSalary" type="number" step="0.01" class="form-control" required>
            </div>
            <div class="col-12 text-end">
              <button class="btn btn-primary">Lưu</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <div class="col-lg-7">
      <div class="card">
        <div class="card-header">Danh sách bậc lương</div>
        <div class="table-responsive">
          <table class="table table-hover align-middle mb-0">
            <thead class="table-light">
            <tr>
              <th>ID</th>
              <th>Tên bậc</th>
              <th>Min</th>
              <th>Max</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="g" items="${grades}">
              <tr>
                <td>${g.id}</td>
                <td>${g.name}</td>
                <td>${g.minSalary}</td>
                <td>${g.maxSalary}</td>
                <td class="text-end">
                  <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/grades/delete?id=${g.id}" onclick="return confirm('Xoá bậc lương này?');">Xoá</a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
