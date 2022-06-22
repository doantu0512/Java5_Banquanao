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
    <table class="table table-success table-striped" >
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Image</th>
            <th scope="col">Name Product</th>
            <th scope="col">Size</th>
            <th scope="col">Color</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Total</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${orderDetail}" var="orderdetail" varStatus="status">
            <tr>
                <td>#${status.count}</td>
                <td><img height="50px" src="${orderdetail.product.image}" alt=""></td>
                <td>${orderdetail.product.name}</td>
                <td>${orderdetail.product.size}</td>
                <td>${orderdetail.product.color}</td>
                <td><fmt:formatNumber value="${orderdetail.price}" pattern="#,###"/> VND</td>
                <td>${orderdetail.quantity}</td>
                <td><span style="color: red"><fmt:formatNumber value="${orderdetail.price*orderdetail.quantity}" pattern="#,###"/> VND</span></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>


