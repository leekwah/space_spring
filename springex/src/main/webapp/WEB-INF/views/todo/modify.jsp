<%--
  Created by IntelliJ IDEA.
  User: kwah
  Date: 2022/11/17
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>read</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!--        <h1>header</h1>-->
        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Features</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Pricing</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled">Disabled</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <form action="/todo/modify" method="post">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control" value='<c:out value="${dto.tno}"></c:out>' readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" value='<c:out value="${dto.title}"></c:out>'>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control" value='<c:out value="${dto.dueDate}"></c:out>'>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="writer" class="form-control" value='<c:out value="${dto.writer}"></c:out>' readonly>
                        </div>

                        <div class="form-check">
                            <label class="form-check-label">
                                Finished &nbsp;
                            </label>
                            <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""}>
                        </div>

                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-danger">Remove</button>
                                <button type="button" class="btn btn-primary">Modify</button>
                                <button type="button" class="btn btn-secondary">List</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">

        <h1>content</h1>
    </div>
    <div class="row footer">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<script>
    const serverValidResult = {}
    <c:forEach items="${errors}" var="error">
        serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>

    console.log(serverValidResult)
</script>
<script>
    const formObj = document.querySelector("form")

    document.querySelector(".btn-danger").addEventListener("click", function (e){
        e.preventDefault()
        e.stopPropagation()

        formObj.action="/todo/remove"
        formObj.method="post"

        formObj.submit()

    }, false);
    document.querySelector(".btn-primary").addEventListener("click", function (e) {
        e.preventDefault()
        e.stopPropagation()

        formObj.action="/todo/modify"
        formObj.method="post"

        formObj.submit()

    }, false)
</script>
<script>
    document.querySelector(".btn-secondary").addEventListener("click", function (e){
        e.preventDefault()
        e.stopPropagation()

        self.location = `/todo/list?${pageRequestDTO.link}`
    }, false)
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>
