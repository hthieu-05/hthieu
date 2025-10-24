<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Chấm công</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
  <h3 class="mb-3">Chấm công tháng ${month}/${year}</h3>
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
    <div class="card-header">Thêm bản ghi</div>
    <div class="card-body">
      <form class="row g-2" method="post" action="${pageContext.request.contextPath}/attendance/save">
        <div class="col-md-2">
          <input class="form-control" name="employeeId" placeholder="Mã NV" required>
        </div>
        <div class="col-md-3">
          <input class="form-control" name="workDate" type="date" required>
        </div>
        <div class="col-md-3">
          <select class="form-select" name="status">
            <option>Present</option>
            <option>Absent</option>
            <option>Leave</option>
          </select>
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
          <th>Ngày</th>
          <th>Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${list}">
          <tr>
            <td>${a.id}</td>
            <td>${a.employeeId}</td>
            <td>${a.workDate}</td>
            <td>${a.status}</td>
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
