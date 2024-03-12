package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectBoardDAO;
import com.bitc.project_msy.database.ProjectBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="view", value="/mvcboard/view.do")
public class ViewController extends HttpServlet {

    //지정한 글번호에 대한 상세 정보를 출력
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //1.글번호 파라미터 값 가져오기
        int idx = Integer.parseInt(req.getParameter("idx"));

        // 2.데이터 베이스 연결
        ProjectBoardDAO dao = new ProjectBoardDAO();
        dao.dbOpen();

        //3.글번호에 해당하는 글의 조회 수 증가
        dao.updateVisitCount(idx);

        // 글번호에 해당하는 글 상세 정보를 가져옴
        ProjectBoardDTO board = dao.selectProjectBoardDetail(idx);
        if(dao.dbIsOpen()) {
            dao.dbClose();
        }

        // request영역에 가져온 데이터 저장
        req.setAttribute("board" , board);

        // jsp템플릿 파일을 호출하여 서버 내에서 화면이동
        req.getRequestDispatcher("/MVCBoard/View.jsp").forward(req,res);
    }
}