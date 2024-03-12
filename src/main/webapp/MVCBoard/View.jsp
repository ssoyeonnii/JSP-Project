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
            $("#btn-list").on("click", function () {
                location.href = "/mvcboard/list.do";
            });

            $("#btn-delete").on("click", function () {
                location.href = "/mvcboard/delete.do?idx=${board.idx}&id=${board.id}";

            });

            $("#btn-update").on("click", function () {
                location.href = "/mvcboard/edit.do?idx=${board.idx}&id=${board.id}";

            });

        });

    </script>

</head>
<body>
<c:import url="../Layout/Menu.jsp"></c:import>

<%@ include file="../Layout/Header.jsp" %>


<main class="container">
    <section>
        <div class="row">
            <div class="col-sm">
                <div class="row mt-3">
                    <div class="col-sm input-group">
                        <span class="input-group-text" style="width: 75px">제목</span>
                        <input type="text" class="form-control" id="title" name="title" value="${board.title}" readonly>
                    </div>
                    <div class="col-sm input-group">
                        <span class="input-group-text">카테고리</span>
                        <input type="text" class="form-control" id="cate" name="cate" value="${board.cate}" readonly>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-sm input-group">
                        <span class="input-group-text">아이디</span>
                        <input type="text" class="form-control" id="id" name="id" value="${board.id}" readonly>
                    </div>
                    <div class="col-sm input-group">
                        <span class="input-group-text">작성일</span>
                        <input type="text" class="form-control" id="postdate" name="postdate" value="${board.postdate}"
                               readonly>
                    </div>
                    <div class="col-sm input-group">
                        <span class="input-group-text">조회수</span>
                        <input type="text" class="form-control" id="visitcount" name="visitcount"
                               value="${board.visitcount}" readonly>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-sm input-group">
                        <span class="input-group-text" style="width: 75px">내용</span>
                            <textarea class="form-control" id="content" name="content" rows="5"
                                      readonly> ${board.content}
                            </textarea>
                    </div>
                </div>
                <c:if test="${not empty board.ofile}">
                    <div class="row mt-3">
                        <div class="col-sm">
                            <div class="border rounded input-group">
                                <span class="input-group-text" style="width: 75px">파일</span>
                                <a href="/mvcboard/download.do?ofile=${board.ofile}&sfile=${board.sfile}" class="btn btn-link">${board.ofile}다운로드</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row mt-3">
                    <div class="col-sm">
                        <button type="button" class="btn btn-outline-dark" id="btn-list">목록</button>
                    </div>
                    <div class="col-sm d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-secondary me-3" id="btn-delete">삭제</button>
                        <button type="button" class="btn btn-outline-success" id="btn-update">수정</button>
                    </div>
                </div>

            </div>
        </div>
    </section>
</main>

</body>

<%@ include file="../Layout/Footer.jsp" %>
</html>
