<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오전 10:48
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

    <style>

    </style>

    <script>
        $(document).ready(function () {
            $("#btn-cancel").on("click", function () {
                history.back();
            });

            $("#btn-list").on("click", function () {
                location.href = "/mvcboard/list.do";
            });

        });
    </script>

</head>
<body>
<c:import url="../Layout/Menu.jsp"></c:import>

<%@ include file="../Layout/Header.jsp" %>

<main class="container">
    <section>
        <div class="row my-3">
            <div class="col-sm">
                <form action="/mvcboard/write.do" method="post" enctype="multipart/form-data">
                    <div>
                    <label>카테고리 : </label><br>
                    <select name="cate" id="cate" class="form-select" style="width: max-content" >
                        <option selected>선택하세요</option>
                        <option value="국내">국내</option>
                        <option value="국외">국외</option>
                    </select>
                    </div>
                    <div class="my-3">
                        <label for="title" class="form-label">제목 : </label>
                        <input type="text" class="form-control" id="title" name = "title" placeholder="제목을 입력하세요" >
                    </div>
                    <div class="my-3">
                        <label for="content" class="form-label">내용 : </label>
                        <textarea class="form-control" id="content" name="content" rows = "5" placeholder="내용을 입력하세요" autofocus></textarea>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm input-group">
                            <input type="file" class="form-control" id="upFile" name="upFile">
                            <button class="btn btn-outline-secondary" type="reset" id="input-group-text">삭제</button>
                        </div>
                    </div>
                    <div class="my-3 d-flex justify-content-center">
                        <button type="submit" class="btn btn-outline-dark me-3">제출</button>
                        <button type="button" class="btn btn-outline-info" id="btn-list">목록</button>
                    </div>
                </form>
            </div>
        </div>
    </section>

</main>

<%@ include file="../Layout/Footer.jsp" %>
</body>
</html>
