<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bảng lương</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
  <h3 class="mb-3">Bảng lương tháng ${month}/${year}</h3>
  <form class="row g-2 mb-3">
    <div class="col-auto">
      <input class="form-control" type="number" name="month" value="${month}" min="1" max="12">
    </div>
    <div class="col-auto">
      <input class="form-control" type="number" name="year" value="${year}" min="2000" max="2100">
    </div>
    <div class="col-auto">
      <button class="btn btn-outline-primary">Xem</button>
    </div>
  </form>

  <div class="card mb-4">
    <div class="card-header">Cập nhật lương</div>
    <div class="card-body">
      <form class="row g-2" method="post" action="${pageContext.request.contextPath}/payroll/save">
        <div class="col-md-2">
          <input class="form-control" name="employeeId" placeholder="Mã NV" required>
        </div>
        <div class="col-md-1">
          <input class="form-control" name="month" type="number" min="1" max="12" value="${month}" required>
        </div>
        <div class="col-md-1">
          <input class="form-control" name="year" type="number" min="2000" max="2100" value="${year}" required>
        </div>
        <div class="col-md-2">
          <input class="form-control" name="baseSalary" type="number" step="0.01" placeholder="Lương cơ bản" required>
        </div>
        <div class="col-md-2">
          <input class="form-control" name="bonus" type="number" step="0.01" placeholder="Thưởng" value="0">
        </div>
        <div class="col-md-2">
          <input class="form-control" name="deduction" type="number" step="0.01" placeholder="Khấu trừ" value="0">
        </div>
        <div class="col-md-2 text-end">
          <button class="btn btn-primary">Lưu</button>
        </div>
      </form>
    </div>
  </div>

  <div class="card">
    <div class="table-responsive">
      <table class="table table-hover mb-0 align-middle">
        <thead class="table-light">
        <tr>
          <th>ID</th>
          <th>Nhân viên</th>
          <th>Tháng</th>
          <th>Năm</th>
          <th>Lương cơ bản</th>
          <th>Thưởng</th>
          <th>Khấu trừ</th>
          <th>Thực nhận</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${list}">
          <tr>
            <td>${p.id}</td>
            <td>${p.employeeId}</td>
            <td>${p.month}</td>
            <td>${p.year}</td>
            <td>${p.baseSalary}</td>
            <td>${p.bonus}</td>
            <td>${p.deduction}</td>
            <td>${p.netSalary}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
