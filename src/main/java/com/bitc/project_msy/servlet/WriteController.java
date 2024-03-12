package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectBoardDAO;
import com.bitc.project_msy.database.ProjectBoardDTO;
import com.bitc.project_msy.util.FileUtil;
import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="write" , value="/mvcboard/write.do")
@MultipartConfig(maxFileSize = 1024*1024*1, maxRequestSize = 1024*1024*10)
public class WriteController extends HttpServlet {

    //단순 view페이지 출력
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if(session.getAttribute("userId") == null ){
            JSFunction.alertLocation("로그인 해주세요" , "../Login/LoginForm.jsp" , res);
        }

        else {
            req.getRequestDispatcher("/MVCBoard/Write.jsp").forward(req, res);
        }
    }

    //DB에 글 등록 프로세스 , 글쓰기-등록버튼 누르면 이동
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();


        //1.파라미터 값 가져오기(한번에 저장하기
        ProjectBoardDTO board = new ProjectBoardDTO();

        board.setTitle(req.getParameter("title"));
        board.setContent(req.getParameter("content"));
        board.setCate(req.getParameter("cate"));
        board.setId(session.getAttribute("userId").toString());

        //2.업로드된 파일 처리(1개의 파일) - 서버의 특정 폴더에 저장
        //저장할 위치 필요
        String saveDir = "C:/upload";

        String originalFileName = "";
        try {
            originalFileName = FileUtil.uploadFile(req, saveDir);
        }
        catch (Exception e) {
            JSFunction.alertLocation("파일 업로드 오류가 발생했습니다.", "/mvcboard/write.do", res);

            return;
            //return안하면 오류 발생했어도 밑에 메소드가 실행되버리기때문에 return값이 있어야함
        }

        //서버에 저장된 파일의 이름 변경
        if (!originalFileName.equals("")) {
            String saveFileName = FileUtil.renameFile(originalFileName, saveDir);

            //원본 파일명과 수정된 파일명을 DTO 객체에 저장
            board.setOfile(originalFileName);
            board.setSfile(saveFileName);

        }


        //3.DB연결
        ProjectBoardDAO dao = new ProjectBoardDAO();
        dao.dbOpen();

        //DB에 데이터 추가
        int result = dao.insertProjectBoard(board);
//        int result=0;
        if (dao.dbIsOpen()) { //열려있으면 true
            dao.dbClose();
        }

        //리다이렉트로 list.do로 이동
        if (result == 1) { //1과 같으면 글쓰기 성공
            res.sendRedirect("/mvcboard/list.do");
        }
        else {
            JSFunction.alertBack("글쓰기에 실패했습니다.", res);
        }

    }

}
