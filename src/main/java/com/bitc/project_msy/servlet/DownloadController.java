package com.bitc.project_msy.servlet;

import com.bitc.project_msy.database.ProjectBoardDAO;
import com.bitc.project_msy.util.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="download" , value="/mvcboard/download.do")
public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //1.파라미터 값으로 전달된 데이터 가져오기
        String ofile = req.getParameter("ofile");
        String sfile = req.getParameter("sfile");

        //2.FileUtill을 활용하여 파일 다운로드
        FileUtil.download(ofile, sfile, "C:/upload", req, res);


    }
}
