<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="m-0">Danh sách nhân viên</h3>
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#employeeModal">Thêm nhân viên</button>
    </div>

    <div class="card">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Họ tên</th>
                    <th>Giới tính</th>
                    <th>Ngày sinh</th>
                    <th>Điện thoại</th>
                    <th>Email</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${employees}">
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.fullName}</td>
                        <td>${e.gender}</td>
                        <td><c:out value="${e.dob}"/></td>
                        <td>${e.phone}</td>
                        <td>${e.email}</td>
                        <td class="text-end">
                            <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/employees/delete?id=${e.id}" onclick="return confirm('Xoá nhân viên này?');">Xoá</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal create/update -->
<div class="modal fade" id="employeeModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <form method="post" action="${pageContext.request.contextPath}/employees/save">
        <div class="modal-header">
          <h5 class="modal-title">Thêm/Sửa nhân viên</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Họ tên</label>
              <input name="fullName" class="form-control" required>
            </div>
            <div class="col-md-3">
              <label class="form-label">Giới tính</label>
              <select name="gender" class="form-select">
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
                <option value="Khác">Khác</option>
              </select>
            </div>
            <div class="col-md-3">
              <label class="form-label">Ngày sinh</label>
              <input type="date" name="dob" class="form-control">
            </div>
            <div class="col-md-6">
              <label class="form-label">CMND/CCCD</label>
              <input name="idCard" class="form-control">
            </div>
            <div class="col-md-6">
              <label class="form-label">Địa chỉ</label>
              <input name="address" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Điện thoại</label>
              <input name="phone" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Email</label>
              <input type="email" name="email" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Chuyên môn</label>
              <input name="specialty" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Trình độ</label>
              <input name="education" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Ngày vào tổ chức</label>
              <input type="date" name="politicalOrgDate" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Ngày vào làm</label>
              <input type="date" name="hireDate" class="form-control">
            </div>
            <div class="col-md-4">
              <label class="form-label">Bậc lương ID</label>
              <input name="gradeId" class="form-control" placeholder="Ví dụ: 1">
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-light" data-bs-dismiss="modal">Đóng</button>
          <button class="btn btn-primary">Lưu</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
