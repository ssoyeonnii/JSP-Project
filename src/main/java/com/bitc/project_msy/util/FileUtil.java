package com.bitc.project_msy.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static String uploadFile(HttpServletRequest req, String saveDir) throws ServletException, IOException {
        //request 내장객체에 저장되어 있는 Multipart 데이터 중 name속성이 uploadFile인 데이터를 가져옴
        Part part = req.getPart("upFile");

        //가져온 정보에서 지정한 header 데이터만 가져옴 (업로드된 파일명이 포함되어있음)
        String partHeader = part.getHeader("content-disposition");
        //가져온 header 데이터를 'filename ='을 기준으로 잘라서 배열에 저장
        String[] phArr = partHeader.split("filename=");
        //잘라낸 파일명의 앞뒤에 빈 공백 제거 및 '\'특수문자 제거 결국 파일명만 남김
        String oriFileName = phArr[1].trim().replace("\"", "");

        if (!oriFileName.isEmpty()) {//파일비었는지 확인, 안비어있어야 저장함
            //업로드된 파일을 지정한 위치에 저장
            part.write(saveDir + File.separator + oriFileName);
        }

        return oriFileName;
    }

    public static String renameFile(String originalFileName, String saveDir) {
        //원본 파일명에서 확장자만 가져옴
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        //현재 시간을 기준으로 문자열 형태를 생성
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        //현재 시간을 기준으로 새 파일명을 생성
        String newFileName = now + ext;

        //서버에 저장되어 있는 기존 파일 선택
        File oldFile = new File(saveDir + File.separator + originalFileName);
        //새 파일명으로 파일 객체 생성
        File newFile = new File(saveDir + File.separator + newFileName);
        //기존 파일명을 새 파일명으로 변환
        oldFile.renameTo(newFile);

        return newFileName;
    }

    public static void download(String ofile, String sfile, String saveDir, HttpServletRequest req, HttpServletResponse res) {

        try {
            //서버에 저장된 파일 선택하기
            File file = new File(saveDir, sfile);
            //InputStream에 선택한 파일 저장
            InputStream inputStream = new FileInputStream(file);

            //한글 파일명 깨짐 방지
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofile = new String(ofile.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                ofile = new String(ofile.getBytes("KSC5601"), "ISO-8859-1");
            }

            //파일 다운로드용 응답 헤더 설정
            res.reset();
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment; filename=\"" + ofile + "\"");
            res.setHeader("Content-Length", "" + file.length());

            //response 내장 객체에 새로운 출력 스트림 생성
            OutputStream outputStream = res.getOutputStream();

            //Inputstream에 저장된 내용을 읽어서 저장할 byte타입의 배열 생성, 파일의 크기만큼의 배열 생성
            byte[] b = new byte[(int) file.length()];
            //몇개의 문자를 가져왔는지 저장
            int readBuffer = 0;
            //InputStream에 저장된 내용을 읽어서 byte타입의 배열에 저장하고 그 크기를 readBuffer에 저장
            while ((readBuffer = inputStream.read(b)) > 0) {
                //byte타입의 배열에 저장된 내용을 0번 index부터 지정한 크기만큼 읽어서 OutputStream에 쓰기
                outputStream.write(b, 0, readBuffer);
            }

            //외부소스 사용 후 닫아주기
            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("예외가 발생했습니다.");
            e.printStackTrace();
        }
    }

    public static void deleteFile(String saveDir, String fileName) { //pass.do-doPost에서 delete 파일 삭제 시 실행하는 메소드임
        // 결과값 받을 필요 없음 , 파일 삭제만 해주면 됨
        File file = new File(saveDir + File.separator + fileName);
        if (file.exists()) {
            file.delete();

        }
    }
}
