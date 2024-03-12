package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectUserDAO;
import com.bitc.project_msy.database.ProjectUserDTO;
import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login/loginform.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String userId = req.getParameter("id");
        String userPass = req.getParameter("pass");

        ProjectUserDAO dao = new ProjectUserDAO();
        dao.dbOpen();

        boolean isMember = dao.isMember(userId, userPass);

        //DAO에서 로그인 확인하는 메소드 필요

        if (isMember == true) {

            ProjectUserDTO projectUserDTO = dao.selectMember(userId);
            dao.dbClose();

            HttpSession session = req.getSession(true);

            session.setAttribute("userId", projectUserDTO.getId());
            session.setAttribute("userName", projectUserDTO.getName());

            session.setMaxInactiveInterval(60 * 5);

            JSFunction.alertLocation(projectUserDTO.getName() + "님 로그인되었습니다.", "../index.jsp", res);

        }
        else {
            JSFunction.alertBack("아이디/비밀번호를 다시 확인해주세요", res);
        }
    }

}





