package com.bitc.project_msy.database;

import jakarta.servlet.ServletContext;

import java.sql.SQLException;

public class ProjectUserDAO extends JDBConnect{

    public ProjectUserDAO(){
        super();
    }
    public ProjectUserDAO(ServletContext app){
        super(app);
    }
    public ProjectUserDAO(String dbDriver, String dbUrl, String dbUserId, String dbUserPw){
        super(dbDriver, dbUrl,dbUserId,dbUserPw);
    }

    //회원가입 메소드
    public int insertMember(ProjectUserDTO user) { //회원가입 완료되면 숫자 반환
        int result = 0;

        try{
            String sql = "INSERT INTO tb_user(id, pass, name) ";
            sql += "VALUES(?, ?, ?) ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPass());
            pstmt.setString(3, user.getName());

            result = pstmt.executeUpdate();


        }
        catch(SQLException e){
            System.out.println("데이터 추가 중 오류가 발생했습니다.");
            System.out.println("SQLException : " + e.getMessage());
        }
        return result;

    }


    //멤버 존재 여부 확인(글 상세보기 시 필요)
    public boolean isMember(String userId, String userPass) {
        boolean result = false;

        try{
            String sql = "SELECT COUNT(*) AS cnt FROM tb_user WHERE id=? AND pass = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,userPass);

            rs = pstmt.executeQuery();

            while(rs.next()){ //데이터 가져오기
                if(rs.getInt("cnt") == 1 ){
                    //cnt 값 출력 1과 같으면 같은 데이터가 있다는 뜻
                    result =true;
                    //기본값을 false로 줘서 else 안해도 됨
                }
            }

        }
        catch (SQLException e){
            System.out.println("데이터 조회 중 오류가 발생했습니다.");
            System.out.println("SQLException : " + e.getMessage());
        }
        return result;
    }


    //로그인
    public ProjectUserDTO selectMember(String userId) {
        //반환해줄 값 memberDto

        ProjectUserDTO projectUserDTO = new ProjectUserDTO();

        try{
            String sql="SELECT id, name FROM tb_user WHERE id = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);

            rs = pstmt.executeQuery();

            //rs에 데이터 저장되어있으므로 while문으로 꺼내기
            while(rs.next()){
                projectUserDTO.setId(rs.getString("id"));
                projectUserDTO.setName(rs.getString("name"));
            }
        }
        catch (SQLException e){
            System.out.println("데이터 조회 중 오류가 발생했습니다.");
            System.out.println("SQLException : " + e.getMessage());
        }
        return projectUserDTO;
        //return되면서 데이터가 들어감

    }




    //사용자 정보 가져오기 메소드(글 작성 시 user id필요)
}
