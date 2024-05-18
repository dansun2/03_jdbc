package com.javaeagles.phone.dao;

import com.javaeagles.phone.dto.PhoneDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.javaeagles.common.JDBCTemplate.close;
import static com.javaeagles.common.JDBCTemplate.getConnection;

public class PhoneRepository {


    private Properties pros = new Properties(); // 쿼리를 읽어오는 프로퍼티 객체
    private Connection con = null; // 데이터베이스 연결을 위한 Connection 객체
    private PreparedStatement pstmt = null; // SQL 쿼리를 실행하기 위한 PreparedStatement 객체
    private PreparedStatement pstmt2 = null; // 또 다른 SQL 쿼리를 실행하기 위한 PreparedStatement 객체
    private ResultSet rset = null; // 쿼리 결과를 저장하는 ResultSet 객체


    public PhoneRepository(){
        try {
            this.pros.loadFromXML(new FileInputStream("src/main/java/com/javaeagles/phone/mapper/phone-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList phoneViewAll(){
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("phoneAll");    // xml에 있는 pros를 참조해서 phoneAll 불러오고 쿼리에 저장.
        con = getConnection();                          // db이랑 연결시켜준다.
        try {
            pstmt = con.prepareStatement(query);        // xml에 있는 명령어들을 데이터베이스와 연결시켜 실행 후 pstmt에 저장
            rset = pstmt.executeQuery();                // executeQuery 쿼리를 실행시켜주는 함수
            while (rset.next()){
                PhoneDTO ph = new PhoneDTO();
                ph.setUserCode(rset.getInt("USER_CODE"));
                ph.setUserName(rset.getString("USER_NAME"));
                ph.setUserEmail(rset.getString("USER_EMAIL"));
                ph.setUserMemo(rset.getString("USER_MEMO"));
                ph.setUserGroup(rset.getString("USER_GROUP"));
                ph.setPhone(rset.getString("PHONE"));
                ph.setPhoneName(rset.getString("PHONE_NAME"));
                arrayList.add(ph);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return arrayList;
    }

    public PhoneDTO phoneFindByName(String name) {
        String query = pros.getProperty("phoneFindByName");     // xml에 있는 phoneFindByName 쿼리 변수에 저장
        con = getConnection();                                  // db연결
        PhoneDTO ph = new PhoneDTO();                           // get, set 쓸려고 만듬

        try {
            pstmt = con.prepareStatement(query); // 쿼리 읽기
            pstmt.setString(1, name); // 1번째 이름 인덱스에 넣어준다.
            rset = pstmt.executeQuery(); // 쿼리 실행
            if(rset.next()){
                ph.setUserCode(rset.getInt("USER_CODE"));
                ph.setUserName(rset.getString("USER_NAME"));
                ph.setUserEmail(rset.getString("USER_EMAIL"));
                ph.setUserMemo(rset.getString("USER_MEMO"));
                ph.setUserGroup(rset.getString("USER_GROUP"));
                ph.setPhone(rset.getString("PHONE"));
                ph.setPhoneName(rset.getString("PHONE_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return ph;
    }

    public PhoneDTO phoneFindById(String index){
        String query = pros.getProperty("phoneFindById");
        con = getConnection();
        PhoneDTO ph = null;

        try {
            pstmt = con.prepareStatement(query); // 불러와
            pstmt.setString(1, index); // 입력해
            rset = pstmt.executeQuery();    // 실행해
            if(rset.next()){
                ph = new PhoneDTO();
                ph.setUserCode(rset.getInt("USER_CODE"));
                ph.setUserName(rset.getString("USER_NAME"));
                ph.setUserEmail(rset.getString("USER_EMAIL"));
                ph.setUserMemo(rset.getString("USER_MEMO"));
                ph.setUserGroup(rset.getString("USER_GROUP"));
                ph.setPhone(rset.getString("PHONE"));
                ph.setPhoneName(rset.getString("PHONE_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return ph;
    }
    public int phoneInsert(PhoneDTO ph) {
        String query = pros.getProperty("phoneInsert");
        String query2 = pros.getProperty("phoneInsert2");
        con = getConnection();
        int result1 = 0;
        int result2 = 0;

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, ph.getUserName());
            pstmt.setString(2, ph.getUserEmail());
            pstmt.setString(3, ph.getUserMemo());
            pstmt.setString(4, ph.getUserGroup());
            if (ph.getUserName() == null || ph.getUserName().isEmpty()) {
                throw new IllegalArgumentException("이름을 입력해주세요.");
            }
            result1 = pstmt.executeUpdate();

            pstmt2 = con.prepareStatement(query2);
            pstmt2.setString(1, ph.getPhone());
            pstmt2.setString(2, ph.getPhoneName());

            if (ph.getPhone() == null || ph.getPhone().isEmpty()) {
                throw new IllegalArgumentException("전화번호를 입력해주세요.");
            }

            result2 = pstmt2.executeUpdate();

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            close(pstmt);
            close(pstmt2);
            close(con);
        }

        return result1 + result2;
    }

    public int phoneModify(String name, String index) {
        // 쿼리불러오기
        String query = pros.getProperty("phoneModify");
        con = getConnection(); // 쿼리 실행
        int result = 0;
        try {
            pstmt = con.prepareStatement(query); // 쿼리 읽기
            pstmt.setString(1, name);
            pstmt.setString(2, index);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return result;

    }
    public int phoneDelete(String name){
        String query = pros.getProperty("phoneDelete");
        con = getConnection();
        int result = 0;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(pstmt);
        }
        return result;
    }
}