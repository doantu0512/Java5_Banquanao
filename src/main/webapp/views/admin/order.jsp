<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 6/10/22
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Card Form</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
</head>
<body>
<div class="container ">
    <c:if test="${empty list.content}">
        <p class="alert alert-warning">
            Vui Lòng Thêm Mới Dữ Liệu
        </p>
    </c:if>
    <c:if test="${!empty sessionScope.message }">
        <div class="alert alert-success mt-3">
                ${ sessionScope.message }
            <c:remove var="message" scope="session"/>
        </div>
    </c:if>
    <c:if test="${!empty sessionScope.error }">
        <div class="alert alert-danger mt-3">
                ${ sessionScope.error }
            <c:remove var="error" scope="session"/>
        </div>
    </c:if>
    <table class="table table-success table-striped" >
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">UserID</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Address</th>
            <th scope="col">DateCreate</th>
            <th scope="col">Total</th>
            <th scope="col">Status</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list.content}" var="order" varStatus="status">
            <tr>
                <td>#${status.count}</td>
                <td>${order.userDatHang ==null ? "Khách vãng lai" : order.userDatHang.username}</td>
                <td>${order.name}</td>
                <td>${order.phone}</td>
                <td>${order.address}</td>
                <td><fmt:formatDate value="${order.createDate}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatNumber value="${order.total}" pattern="#,###"/>VND</td>
                <td> <c:choose>
                    <c:when test="${order.status ==0}">
                        <button data-toggle="modal" data-target="#aa${order.id}" class="btn btn-success">Confirm</button>
                        <button data-toggle="modal" data-target="#cc${order.id}" class="btn btn-danger">Cancel</button>
                    </c:when>
                    <c:when test="${order.status ==1}"><span style="color: green">Confirmed</span></c:when>
                    <c:when test="${order.status ==2}"><span style="color: red">Cancelled</span></c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
                <td>
                    <a href="/admin/order/showdetail?id=${order.id}" class="btn btn-warning">View</a>
                </td>
                <td>
                    <button data-toggle="modal" data-target="#kak${order.id}" class="btn btn-danger">Delete</button>
                </td>
                <div id="kak${order.id}" class="modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Xác nhận</h3>
                                <button type="button" class="btn-close" data-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5>Bạn muốn xóa Order ${order.id} ?</h5>
                            </div>
                            <div class="modal-footer">
                                <form action="/admin/order/delete" method="post">
                                    <input type="hidden" value="${order.id}" name="id">
                                    <button class="btn btn-danger">Xóa</button>
                                </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                        aria-label="Close">Hủy
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="cc${order.id}" class="modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Xác nhận</h3>
                                <button type="button" class="btn-close" data-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5> Hủy Đơn  ${order.id} ?</h5>
                            </div>
                            <div class="modal-footer">
                                <form action="/admin/order/cancel" method="post">
                                    <input type="hidden" value="${order.id}" name="id">
                                    <button class="btn btn-success">Xác Nhận</button>
                                </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                        aria-label="Close">Hủy
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="aa${order.id}" class="modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Xác nhận</h3>
                                <button type="button" class="btn-close" data-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5>Xác Nhận Đơn hang nay ${order.id} ?</h5>
                            </div>
                            <div class="modal-footer">
                                <form action="/admin/order/confirm" method="post">
                                    <input type="hidden" value="${order.id}" name="id">
                                    <button class="btn btn-success">Xác Nhận</button>
                                </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                        aria-label="Close">Hủy
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
        <div class="col-6">
            <p class="d-flex justify-content-start">
                Trang Số : <span style="color: red">&nbsp ${list.number + 1} </span> &nbsp trong tổng số : <span
                    style="color: red">&nbsp${list.totalPages} </span>
            </p>
            <p class="d-flex justify-content-start">
                Hiển Thị : <span style="color: red"> &nbsp${list.numberOfElements} </span> &nbsp trong tổng số : <span
                    style="color: red"> &nbsp${list.totalElements} </span> &nbsp sản phẩm
            </p>
        </div>
        <nav aria-label="Page navigation example" class="d-flex justify-content-end col-6">
            <ul class="pagination">
                <c:if test="${list.number-1>0}">
                    <c:set var="number" scope="session" value="?page=${list.number -1}"></c:set>
                </c:if>
                <c:if test="${list.number-1<1}">
                    <c:set var="number" scope="session" value=""></c:set>
                </c:if>
                <c:if test="${list.number+1>list.totalPages}">
                    <c:set var="numberup" scope="session" value="?page=${list.totalPages}"></c:set>
                </c:if>
                <c:if test="${list.number+1<list.totalPages}">
                    <c:set var="numberup" scope="session" value="?page=${list.number+1}"></c:set>
                </c:if>
                <li class="page-item"><a class="page-link" href="/admin/card/index${number} ">Previous</a></li>
                <c:forEach var="i" begin="0" end="${ list.totalPages - 1 }">
                    <li class="page-item"><a class="page-link" href="/admin/order/index?page=${ i }">${ i + 1 }</a></li>
                    </li>
                </c:forEach>
                <li class="page-item"><a class="page-link" href="/admin/card/index${numberup}">Next</a></li>
            </ul>
        </nav>
    </div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>


