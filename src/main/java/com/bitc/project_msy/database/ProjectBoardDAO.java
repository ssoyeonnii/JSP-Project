package com.bitc.project_msy.database;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectBoardDAO extends JDBConnect{

    public ProjectBoardDAO(){
        super ();
    }
    public ProjectBoardDAO(ServletContext app){
        super (app);
    }
    public ProjectBoardDAO(String dbDriver, String dbUrl, String dbUserId, String dbUserPass){
        super(dbDriver, dbUrl, dbUserId, dbUserPass);
    }

    //  전체 게시물 수 가져오기
    public int totalCount() {
        int result = 0;

        try {
            String sql = "SELECT COUNT(*) AS cnt FROM tb_board ";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result = rs.getInt("cnt");
            }
        }
        catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return result;
    }

    //게시글 전체 리스트 가져오기 ArrayList
    public List<ProjectBoardDTO> selectBoardList() {
        List<ProjectBoardDTO> boardList = new ArrayList<>();

        try{
            String sql="SELECT idx, cate, title, id, postdate, visitcount ";
            sql += "FROM tb_board ORDER BY idx DESC ";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

            while(rs.next()){
                ProjectBoardDTO board = new ProjectBoardDTO();

                board.setIdx(rs.getInt("idx"));
                board.setCate(rs.getString("cate"));
                board.setTitle(rs.getString("title"));
                board.setId(rs.getString("id"));
                board.setPostdate(rs.getString("postdate"));
                board.setVisitcount(rs.getInt("visitcount"));

                boardList.add(board);
            }

        }
        catch(SQLException e){
            printErrorMessage("조회", e.getMessage());
        }

        return boardList;

    }

    //  전체 게시물 목록 가져오기 (페이징 기능 추가)
    public List<ProjectBoardDTO> selectProjectBoardListPaging(int startNum, int postSize) {
        List<ProjectBoardDTO> boardList = new ArrayList<>();

        try {
            String sql = "SELECT idx, cate, title, id, postdate, visitcount ";
            sql += "FROM tb_board ";
            sql += "ORDER BY idx DESC ";
            sql += "LIMIT ?, ? "; // LIMIT를 사용하여 지정한 index부터 지정한 수만큼 데이터를 조회하여 가져옴

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            // 데이터를 가져오기 시작할 index, index는 0부터 시작
            pstmt.setInt(2, postSize);
            // 가져올 데이터 수 지정

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProjectBoardDTO board = new ProjectBoardDTO();

                board.setIdx(rs.getInt("idx"));
                board.setCate(rs.getString("cate"));
                board.setTitle(rs.getString("title"));
                board.setId(rs.getString("id"));
                board.setPostdate(rs.getString("postdate"));
                board.setVisitcount(rs.getInt("visitcount"));

                boardList.add(board);
            }
        }
        catch (SQLException e) {
            printErrorMessage("조회", e.getMessage());
        }

        return boardList;
    }


    //게시글 수정하기
    public int updateProjectBoard(ProjectBoardDTO board) {
        int result = 0;

        try{
            String sql = "UPDATE tb_board SET title = ?, content =?,ofile = ? , sfile = ? ";
            sql += "WHERE idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,board.getTitle());
            pstmt.setString(2,board.getContent());
            pstmt.setString(3,board.getOfile());
            pstmt.setString(4,board.getSfile());
            pstmt.setInt(5,board.getIdx());

            result = pstmt.executeUpdate();

        }
        catch (SQLException e){
            System.out.println("데이터 수정 중 오류가 발생했습니다.");
            System.out.println("SQLException : " + e.getMessage());
        }
        return result;

    }

    //게시글 삭제하기

    public int deleteProjectBoard(int idx){
        int result = 0;

        try {
            String sql = "DELETE FROM tb_board WHERE idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idx);
            //매개변수로 받은 num값
            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("데이터 삭제 중 오류가 발생했습니다.");
            System.out.println("SQLException : " + e.getMessage());
        }

        return result;
    }


    //게시글 상세보기

    public ProjectBoardDTO selectProjectBoardDetail(int idx) {
        ProjectBoardDTO board = new ProjectBoardDTO();

        try{
            String sql = "SELECT idx, cate, title, content, id, postdate, ofile, sfile, visitcount ";
            sql += "FROM tb_board WHERE idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();

            while(rs.next()){
                board.setIdx(rs.getInt("idx"));
                board.setCate(rs.getString("cate"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setId(rs.getString("id"));
                board.setPostdate(rs.getString("postdate"));
                board.setOfile(rs.getString("ofile"));
                board.setSfile(rs.getString("sfile"));
                board.setVisitcount(rs.getInt("visitcount"));
            }
        }
        catch (SQLException e){
            printErrorMessage("조회" , e.getMessage());
        }

        return board;
    }

    //게시글 작성하기
    public int insertProjectBoard(ProjectBoardDTO board) { //board의 값을 받음
        int result = 0;
        try{
            String sql="INSERT INTO tb_board(title, content, cate, id, ofile, sfile, postdate) ";
            sql += "VALUES (?, ?, ?, ?, ?, ?, NOW()) ";

            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2,board.getContent());
            pstmt.setString(3,board.getCate());
            pstmt.setString(4,board.getId());
            pstmt.setString(5,board.getOfile());
            pstmt.setString(6,board.getSfile());

            result = pstmt.executeUpdate();

        }
        catch (SQLException e){
            printErrorMessage("등록" , e.getMessage());
        }
        return result;

    }


    //조회 수 증가 메소드
    public void updateVisitCount(int idx){

        try{
            String sql = "UPDATE tb_board SET visitcount = visitcount+1 WHERE idx = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);

            pstmt.executeUpdate();
            //반환값이 x = void
        }
        catch (SQLException e){
            printErrorMessage("증가" , e.getMessage());
        }
    }


    private void printErrorMessage(String userMsg, String errMsg) {
        System.out.println("\n********************\n");
        System.out.println("데이터베이스 " + userMsg + "중 오류가 발생했습니다.");
        System.out.println("오류내용 " + errMsg);
        System.out.println("\n********************\n");

    }
}
