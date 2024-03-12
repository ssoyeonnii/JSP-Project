package com.bitc.project_msy.servlet;

import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="logout" , value="/servlet/LogoutController.do")
public class LogoutController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        session.removeAttribute("userId");
        session.removeAttribute("userName");

        session.invalidate();

//  메시지 출력 후 목록 페이지로 이동
        JSFunction.alertLocation("로그아웃 되었습니다.", "../index.jsp", res);
    }

}
