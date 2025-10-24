<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Khen thưởng/Kỷ luật</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
  <h3 class="mb-3">Khen thưởng/Kỷ luật của nhân viên #${employeeId}</h3>

  <div class="card mb-4">
    <div class="card-header">Thêm mới</div>
    <div class="card-body">
      <form class="row g-2" method="post" action="${pageContext.request.contextPath}/discipline/save">
        <input type="hidden" name="employeeId" value="${employeeId}">
        <div class="col-md-3">
          <select class="form-select" name="type">
            <option>Reward</option>
            <option>Punish</option>
          </select>
        </div>
        <div class="col-md-4">
          <input class="form-control" name="note" placeholder="Ghi chú">
        </div>
        <div class="col-md-3">
          <input class="form-control" type="date" name="entryDate" required>
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
          <th>Loại</th>
          <th>Ghi chú</th>
          <th>Ngày</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${list}">
          <tr>
            <td>${d.id}</td>
            <td>${d.type}</td>
            <td>${d.note}</td>
            <td>${d.entryDate}</td>
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
