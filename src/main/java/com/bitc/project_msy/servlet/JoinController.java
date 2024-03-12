package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectUserDAO;
import com.bitc.project_msy.database.ProjectUserDTO;
import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="join", value="/login/join.do")
public class JoinController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        String userId = req.getParameter("id");
        String userPass = req.getParameter("pass");
        String userName = req.getParameter("name");

        ProjectUserDTO member = new ProjectUserDTO();
        member.setId(userId);
        member.setPass(userPass);
        member.setName(userName);

        //db연결 및 제어위한 dao타입 객체 생성
        ProjectUserDAO dao = new ProjectUserDAO();
        dao.dbOpen();
        //dto 객체를 매개변수로 사용하는 insertMember를 실행하여 회원 가입
        int result = dao.insertMember(member);
        dao.dbClose();

        if(result > 0){
//        회원 가입 성공 시 로그인 페이지로 이동
            JSFunction.alertLocation("회원 가입되었습니다." , "../Login/LoginForm.jsp",res);
        }
        else {
            //회원 가입 실패 시 회원 가입 페이지로 돌아가기
            JSFunction.alertBack("회원 가입 중 오류가 발생했습니다.", res);
        }
    }

}
