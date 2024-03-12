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

            $("#title").focus();
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
                <form action="/mvcboard/edit.do" method="post" enctype="multipart/form-data" >
                    <input type="hidden" name="idx" value="${board.idx}">
                    <input type="hidden" name="oldSaveFile" value="${board.sfile}">
                    <div class="row mt-3 input-group">
                        <div class="col-sm input-group">

                            <label class="input-group-text" for="title" style="width: 97px">제목</label>
                            <input type="text" class="form-control" id="title" name="title" value="${board.title}">
                        </div>
                        <c:choose>
                            <c:when test="${board.cate == null}">
                                <div class="col-sm input-group">
                                    <label class="input-group-text">카테고리</label>
                                    <select name="cate" id="cate" class="form-select">
                                        <option selected>----- 선택하세요 -----</option>
                                        <option value="국내">국내</option>
                                        <option value="국외">국외</option>
                                    </select>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${board.cate eq '국내'}">
                                        <div class="col-sm input-group">
                                            <label class="input-group-text">카테고리</label>
                                            <select name="cate" id="cate2" class="form-select">
                                                <option >----- 선택하세요 -----</option>
                                                <option value="국내" selected>국내</option>
                                                <option value="국외">국외</option>
                                            </select>
                                        </div>
                                    </c:when>
                                    <c:when test="${board.cate eq '국외'}">
                                        <div class="col-sm input-group">
                                            <label class="input-group-text">카테고리</label>
                                            <select name="cate" id="cate3" class="form-select">
                                                <option >----- 선택하세요 -----</option>
                                                <option value="국내">국내</option>
                                                <option value="국외" selected>국외</option>
                                            </select>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-sm input-group">
                                            <label class="input-group-text">카테고리</label>
                                            <select name="cate" id="cate4" class="form-select">
                                                <option selected>----- 선택하세요 -----</option>
                                                <option value="국내">국내</option>
                                                <option value="국외">국외</option>
                                            </select>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
<%--                        <div class="col-sm">--%>
<%--                            <input type="text" class="form-control" id="cate" name="cate" value="${board.cate}" readonly>--%>
<%--                        </div>--%>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm input-group">
                            <label class="input-group-text" style="width: 97px">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" value="${board.id}" readonly>
                        </div>
                        <div class="col-sm input-group">
                            <label class="input-group-text">작성일</label>
                            <input type="text" class="form-control" id="postdate" name="postdate" value="${board.postdate}" readonly>
                        </div>
                        <div class="col-sm input-group">
                            <label class="input-group-text">조회수</label>
                            <input type="text" class="form-control" id="visitcount" name="visitcount"
                                   value="${board.visitcount}" readonly>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm input-group">
                            <label class="input-group-text" style="width: 97px">내용</label>
                            <textarea class="form-control" id="content" name="content" rows="5">${board.content}</textarea>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm input-group">
                            <input type="file" class="form-control" id="upFile" name="upFile">
                            <button class="btn btn-outline-secondary" type="reset" id="input-group-text">삭제</button>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-sm">
                            <button type="button" class="btn btn-outline-dark" id="btn-list">목록</button>
                        </div>
                        <div class="col-sm d-flex justify-content-end">
                            <button type="submit" class="btn btn-outline-success" id="btn-update">등록</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>

</main>

<%@ include file="../Layout/Footer.jsp" %>
</body>
</html>
