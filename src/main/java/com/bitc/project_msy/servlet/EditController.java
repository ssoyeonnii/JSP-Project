package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectBoardDAO;
import com.bitc.project_msy.database.ProjectBoardDTO;
import com.bitc.project_msy.database.ProjectUserDTO;
import com.bitc.project_msy.util.FileUtil;
import com.bitc.project_msy.util.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.View;
import java.io.IOException;

@WebServlet(name="edit" , value="/mvcboard/edit.do")
@MultipartConfig(maxFileSize = 1024*1024*1, maxRequestSize = 1024*1024*10)
public class EditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String id = req.getParameter("id");

        if(session.getAttribute("userId") == null ){
            JSFunction.alertLocation("로그인 해주세요" , "../Login/LoginForm.jsp" , res);
        }

        if(id.equals(session.getAttribute("userId").toString()) == false) {
            //작성자와 로그인한 유저가 다를 경우 메시지 출력 후 이전 페이지로 이동
            JSFunction.alertBack("작성자만 수정할 수 있습니다." , res);

        }
        else {

//     1.  글 번호를 파라미터 값으로 가져오기
            int idx = Integer.parseInt(req.getParameter("idx"));

//     2.  데이터 베이스 연결
            ProjectBoardDAO dao = new ProjectBoardDAO();
            dao.dbOpen();

//     3.  글 번호에 대한 상세 정보(전부 다)를 가져옴
            req.setAttribute("board", dao.selectProjectBoardDetail(idx));

//     4.  템플릿 파일을 호출하여 화면 이동 및 데이터 전달
            req.getRequestDispatcher("/MVCBoard/Edit.jsp").forward(req, res);
        }
    }

    //수정된 정보를 받아서 데이터 베이스를 수정
    //doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        //1.수정할 정보를 파라미터 값으로 가져옴

        String title = req.getParameter("title"); //수정할 타이틀


        String content = req.getParameter("content"); //수정할 컨텐츠 내용
        int idx = Integer.parseInt(req.getParameter("idx")); //데이터를 수정할 글 번호
        String oldSaveFile = req.getParameter("oldSaveFile");


        //1-1.가져온 정보를 DTO 타입으로 저장(idx, title, content만 저장)
        ProjectBoardDTO board = new ProjectBoardDTO();
        board.setIdx(idx);
        board.setTitle(title);
        board.setContent(content);

        //새로 업로드 된 파일 처리
        String saveDir = "C:/upload";

        String oriFileName = "";
        try{
            oriFileName = FileUtil.uploadFile(req,saveDir);
        }
        catch (Exception e){
            JSFunction.alertBack("파일 업로드 중 오류입니다." , res);
            return;
        }

        //새로 업로드 된 파일명 수정
        if(!oriFileName.equals("")) {
            String saveFileName = FileUtil.renameFile(oriFileName,saveDir);

            //새로 업로드 된 파일명 DTO 객체에 저장
            board.setOfile(oriFileName);
            board.setSfile(saveFileName);

            //이전파일 삭제해줘야함
            FileUtil.deleteFile(saveDir,oldSaveFile);
        }

        //2.DB 연결
        ProjectBoardDAO dao = new ProjectBoardDAO();
        dao.dbOpen();

        //3.전달받은 내용으로 데이터 업데이트
        int result = dao.updateProjectBoard(board);
        if(dao.dbIsOpen()){
            dao.dbClose();
        }

        //4.수정된 화면으로 이동원래 위치로
        if(result ==1 ){
            //수정 성공 시 수정된 화면으로 돌아가기
            res.sendRedirect("/mvcboard/view.do?idx=" + idx);
        }
        else{
            //수정 실패 시
            JSFunction.alertLocation("게시글 수정이 실패했습니다." , "/mvcboard/view.do?idx=" + idx , res);
        }
    }
}
