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

    </script>

</head>
<body>
<c:import url="../Layout/Menu.jsp"></c:import>

<%@ include file="../Layout/Header.jsp" %>

<main class="container">
    <section>
        <div class="row">
            <div class="col-sm">
                <table class="table table-hover text-center">
                    <colgroup>
                        <col style="width : 10%">
                        <col style="width : 10%">
                        <col style="width : 40%">
                        <col style="width : 15%">
                        <col style="width : 15%">
                        <col style="width : 10%">
                    </colgroup>

                    <thead class="table-light">
                    <tr>
                        <th>번호</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>아이디</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:choose>
                        <c:when test="${empty boardList}">
                            <tr>
                                <td colspan="5">등록된 게시물이 없습니다.</td>
                            </tr>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="item" items="${boardList}" varStatus="loop">
                                <tr>
                                    <td>${item.idx}</td>
                                    <td>${item.cate}</td>
                                    <td class="text-start title">
                                        <a href="/mvcboard/view.do?idx=${item.idx}"
                                           class="text-decoration-none text-dark">${item.title}</a>
                                    </td>
                                    <td>${item.id}</td>
                                    <td>${item.postdate}</td>
                                    <td>${item.visitcount}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="5" class="border-bottom-0">
                            <ul class="pagination justify-content-center">
                                <c:if test="${startPageNum ne 1}">
                                    <li class="page-item"><a class="page-link text-dark bg-light" href="/mvcboard/list.do?pageNum=${startPageNum - 1}">Prev</a>
                                    </li>
                                </c:if>

                                <c:forEach var="i" begin="${startPageNum}" end="${endPageNum}" step="1">
                                    <c:choose>
                                        <c:when test="${i eq pageNum}">
                                            <li class="page-item active ">
                                                <a class="page-link text-dark bg-light"> ${i}
                                                </a>
                                            </li>
                                        </c:when>

                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link text-dark bg-light" href="/mvcboard/list.do?pageNum=${i}">${i}
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${endPageNum lt totalPage}">
                                    <li class="page-item">
                                        <a class="page-link text-dark bg-light" href="/mvcboard/list.do?pageNum=${endPageNum + 1}">Next
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-sm">
                <div class="d-flex justify-content-end">
                    <a href="/mvcboard/write.do" class="btn btn-secondary">글쓰기</a>
                </div>
            </div>
        </div>
    </section>
</main>

</body>
<%@ include file="../Layout/Footer.jsp" %>
</html>
