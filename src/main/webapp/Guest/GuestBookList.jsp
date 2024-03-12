<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-22
  Time: 오전 9:26
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


<main class="container mt-5">
    <section>
        <div class="row">
            <div class="col-sm">
                <table class="table table-hover table-striped text-center">
                    <colgroup>
                        <col style="width : 10%">
                        <col style="width : 45%">
                        <col style="width : 15%">
                        <col style="width : 20%">
                        <col style="width : 10%">
                    </colgroup>

                    <thead>
                    <tr>
                        <th>글번호</th>
                        <th>글제목</th>
                        <th>글쓴이</th>
                        <th>등록일</th>
                        <th>조회수</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td colspan="5">등록된 게시물이 없습니다.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-sm">
                <div class="d-flex justify-content-end">
                    <a href="/Guest/GuestBook.jsp" class="btn btn-primary">글쓰기</a>
                </div>
            </div>
        </div>
    </section>
</main>

</body>

<%@ include file="../Layout/Footer.jsp" %>
</html>
