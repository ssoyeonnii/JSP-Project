<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오전 11:14
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

    </script>

</head>
<body>
<c:import url="../Layout/Menu.jsp"></c:import>

<%@ include file="../Layout/Header.jsp" %>

<main class="container">
    <section>
        <div class="row">
            <div class="col-sm-4 mx-auto">
                <form action="/login/loginform.do" method="post">
                    <div class="my-3 form-floating">
                        <input type="text" class="form-control" id="id" name="id" placeholder="">
                        <label for="id">아이디</label>
                    </div>
                    <div class="my-3 form-floating">
                        <input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호를 입력하세요">
                        <label for="pass">비밀번호</label>
                    </div>
                    <div class="my-3 d-grid">
                        <button type="submit" class="btn btn-outline-dark btn-outline-info btn-lg">로그인</button>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <a href="" class="text-decoration-none text-dark">
                                아이디/비밀번호 찾기
                            </a>
                        </div>
                        <div class="col-sm justify-content-end text-end">
                            <a href="./Join.jsp" class="text-decoration-none text-dark">회원 가입</a>
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
