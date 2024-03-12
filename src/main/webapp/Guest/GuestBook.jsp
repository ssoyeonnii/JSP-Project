<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-21
  Time: 오후 3:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

    <style>

    </style>

    <script>

    </script>

</head>
<body>
<c:import url="../Layout/Menu.jsp"></c:import>

<%@ include file="../Layout/Header.jsp" %>
<main class="container">
    <section>
        <div class="row">
            <div class="col-sm">
                <form action="" method="post" enctype="multipart/form-data">
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요.">
                                <label for="name">이름을 입력하세요</label>
                            </div>
                        </div>
                        <div class="col-sm">
                            <div class="form-floating">
                                <input type="password" class="form-control" id="pass" name="pass"
                                       placeholder="비밀번호를 입력하세요">
                                <label for="pass">비밀번호를 입력하세요</label>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
                                <label for="title">제목을 입력하세요</label>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="form-floating">
                                <textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요"
                                          style="height : 200px"></textarea>
                                <label for="content">내용을 입력하세요</label>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm d-flex justify-content-end">
                            <button type="reset" class="btn btn-secondary me-2" id="btn-cancel">취소</button>
                            <button type="submit" class="btn btn-success">등록</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>


</body>

<%@ include file="../Layout/Footer.jsp" %>
</html>
