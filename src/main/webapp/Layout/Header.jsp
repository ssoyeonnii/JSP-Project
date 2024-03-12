<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-17
  Time: 오전 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%

    String pageName = "";

    String result = request.getRequestURI();
    int index = result.lastIndexOf("/") + 1;
    //http://localhost:8080/mvcboard/write.do 에서 write.do만 가져옴
    result = result.substring(index);
    // System.out.println(fileName);

    switch (result) {
        case "List.jsp":
            pageContext.setAttribute("pageName" , "최근 작성된 게시글");
            break;

        case "Write.jsp":
            pageContext.setAttribute("pageName" , "작성하기");
            break;

        case "View.jsp":
            pageContext.setAttribute("pageName" , "상세보기");
            break;

        case "Edit.jsp":
            pageContext.setAttribute("pageName" , "수정하기");
            break;

        case "Join.jsp":
            pageContext.setAttribute("pageName" , "회원가입");
            break;

        case "LoginForm.jsp":
            pageContext.setAttribute("pageName" , "Login");
            break;

        case "GuestBook.jsp":
            pageContext.setAttribute("pageName" , "방명록 작성하기");
            break;

        case "GuestBookList.jsp":
            pageContext.setAttribute("pageName" , "방명록");
            break;
    }

%>

<header class="container-fluid p-4">
    <div class="container bg-opacity-10 p-5" >
        <h1 class="text-center m-5">${pageName}</h1>
        <hr>
    </div>
</header>
