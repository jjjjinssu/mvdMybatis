package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository {
    // 마리아 디비 연결
    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    //jdbc 생성자 생성
    public ScoreJdbcRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    //스코어객체 반환
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM tbl_score"; // 방식은 비슷비슷한데 sql 방법이 조금씩 다르다 (조회)

            PreparedStatement pstmt = conn.prepareStatement(sql); //sql 쿼리를 실행하기 위한 사전 컴파일러

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { //한줄 한줄 넘어가는
                scoreList.add(new Score(rs));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override

    //방법이 2~3가지 있음 옛날버전 부터 간소화 까지..
    public boolean save(Score score) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql = "INSERT INTO tbl_score " +
                    " (stu_name, kor, eng, math, total, average, grade) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate(); // 성공시 1, 실패시 0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql = "DELETE FROM tbl_score WHERE stu_num=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            int result = pstmt.executeUpdate(); // 성공시 1, 실패시 0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Score findByStuNum(int stuNum) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM tbl_score WHERE stu_num=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Score(rs);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
