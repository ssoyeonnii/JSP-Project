package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectBoardDAO;
import com.bitc.project_msy.database.ProjectBoardDTO;
import com.bitc.project_msy.util.FileUtil;
import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="delete", value="/mvcboard/delete.do" )
public class DeleteController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String id = req.getParameter("id");

        if(session.getAttribute("userId") == null ){
            JSFunction.alertLocation("로그인 해주세요" , "../Login/LoginForm.jsp" , res);
            return;
        }

        if(id.equals(session.getAttribute("userId").toString()) == false) {
            //작성자와 로그인한 유저가 다를 경우 메시지 출력 후 이전 페이지로 이동
            JSFunction.alertBack("작성자만 삭제할 수 있습니다." , res);
            return;

        }
        else {

//     1.  글 번호를 파라미터 값으로 가져오기
            int idx = Integer.parseInt(req.getParameter("idx"));


//     2.  데이터 베이스 연결 해서 첨부파일 먼저 삭제 후 db에서 deleteProjectBoard로 전체 삭제
            ProjectBoardDAO dao = new ProjectBoardDAO();
            dao.dbOpen();

            //첨부파일 삭제
            ProjectBoardDTO board = dao.selectProjectBoardDetail(idx);
            
            FileUtil.deleteFile("C:/upload", board.getSfile());

            //db에서 관련 idx 데이터 전체 삭제
            dao.deleteProjectBoard(idx);
            
            if(dao.dbIsOpen()){
                dao.dbClose();
            }

//     4.  템플릿 파일을 호출하여 화면 이동 및 데이터 전달

            JSFunction.alertLocation("삭제되었습니다." , "/mvcboard/list.do" , res);
        }

    }

}
