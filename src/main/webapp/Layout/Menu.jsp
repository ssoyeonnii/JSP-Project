<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오전 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<nav class="navbar navbar-expand-sm fixed-top border-bottom">
    <div class="container-fluid ">

        <a href="../index.jsp" class="navbar-brand">여행Gallery</a>

        <ul class="navbar-nav">
            <li class="nav-item text-black">
                <a class="nav-link" href="#">국내</a>
            </li>
            <li class="nav-item text-black">
                <a class="nav-link" href="#">국외</a>
            </li>
            <li class="nav-item text-black">
                <a class="nav-link" href="../Guest/GuestBookList.jsp">방명록</a>
            </li>
        </ul>

        <div class="ms-auto">
            <%
                //세션 정보를 가지고 로그인 상태를 확인
                // 로그인 상태에 따라 화면 UI 변경
                if (session.getAttribute("userId") == null ) { //빈문자열이면 로그인x상태이므로 로그인하라고 로그인 서블릿으로 전송
                    out.print("<a href='../Login/LoginForm.jsp' class='btn btn-outline-dark'>Login</a>");
                }
                else{
            %>

            <span class="text navbar-text me-3 text-dark"><%=session.getAttribute("userName")%>님 </span>
            <a href="/servlet/LogoutController.do" class="btn btn-outline-secondary">Logout</a>

            <%
                }
            %>
        </div>
    </div>
</nav>





