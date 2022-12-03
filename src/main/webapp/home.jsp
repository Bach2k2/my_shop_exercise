<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: letro
  Date: 11/30/2022
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <%--    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          id="bootstrap-css">--%>
    <%--    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>
    <%--    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!------ Include the above in your HEAD tag ---------->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <style>

    </style>
</head>
<body>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">Supermarket</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:if test="${myUser==null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/home?action=login">Login</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">Account</a>
                </li>
                <c:if test="${myUser!=null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Hello ${myUser}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/home?action=logout">Logout</a>
                    </li>
                </c:if>
            </ul>

            <form action="/home?action=search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="keyword" type="text" class="form-control" aria-label="Small"
                           aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <a class="btn btn-success btn-sm ml-3" href="/home?action=add">
                    <i class="fa fa-shopping-cart"></i> Add a product
                    <span class="badge badge-light">10</span>
                </a>
            </form>
        </div>
    </div>
</nav>
<%--Title begin--%>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Siêu thị thời trang chất lượng cao</h1>
        <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm nhập từ Việt Nam</p>
    </div>

</section>
<%--Title end--%>
<!--end of menu-->
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="/home?action=get">Product List</a></li>
                    <li class="breadcrumb-item active"><a href="/home?action=add">Add product</a></li>
                    <li class="breadcrumb-item"><a href="/home?action=getC">Category</a></li>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories
                </div>
                <ul class="list-group category_block">
                    <c:forEach items="${cList}" var="category">
                        <li class="list-group-item text-white"><a href="#">${category.cName}</a></li>
                    </c:forEach>

                </ul>
            </div>
            <div class="card bg-light mb-3">
                <div class="card-header bg-success text-white text-uppercase">Last product</div>
                <div class="card-body">
                    <img class="img-fluid" src="${p.image}"/>
                    <h5 class="card-title">${p.name}</h5>
                    <%--                    <p class="card-text">${p.title}</p>--%>
                    <p class="bloc_left_price">${p.price} $</p>
                </div>
            </div>
        </div>

        <div class="col-sm-9">
            <div class="row">
                <c:forEach items="${pList}" var="p">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <img class="card-img-top" src="${p.image}" alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title show_txt"><a href="#" title="View Product">${p.name}</a></h4>
                                    <%--                                <p class="card-text show_txt">${p.title}</p>--%>
                                <div class="row">
                                    <div class="col">
                                        <p class="btn btn-danger btn-block">${p.price} $</p>
                                    </div>
                                    <div class="col">
                                        <a href="/home?action=update&pId=${p.pId}" class="btn btn-success btn-block">Update</a>
                                        <a class="btn btn-danger btn-block" data-toggle="modal"
                                           data-target="#exampleModal">Delete</a>
                                        <!-- Modal -->
                                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Delete this
                                                            product</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p> Bạn chắc chán muốn xóa sản phẩm <c:out
                                                                value="${p.name}"></c:out></p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">No
                                                        </button>
                                                        <a href="/home?action=delete&pId=${p.pId}"
                                                           class="btn btn-danger btn-block">Delete</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- end Modal -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>

</body>
</html>
