package com.bitc.project_msy.util;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

import java.io.PrintWriter;

public class JSFunction {
    //JspWriter로 받을 때
    public static void alertLocation(String msg, String url, JspWriter out) {

        try {
            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "location.href = '" + url + "';";
            script += "</script>";

            out.print(script);
        }
        catch (Exception e) {

        }
    }

    //화면에 출력할 메시지를 입력받아 메시지 출력 및 이전 페이지로 이동을 위한 정적 메소드
    public static void alertBack(String msg, JspWriter out) {
        try{
            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "history.back();";
            script += "</script>";

            out.print(script);
        }
        catch(Exception e){

        }
    }
    
    //HttpServletResponse 로 받을 때

    public static void alertLocation(String msg, String url, HttpServletResponse res){
        try{
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();

            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "location.href = '" + url + "';";
            script += "</script>";

            out.print(script);
        }
        catch (Exception e){

        }

    }

    public static void alertBack(String msg, HttpServletResponse res){
        try{
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();

            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "history.back();";
            script += "</script>";

            out.print(script);

        }
        catch (Exception e){

        }
    }
}


