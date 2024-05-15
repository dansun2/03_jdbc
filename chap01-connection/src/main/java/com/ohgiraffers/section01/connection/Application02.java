package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {
    public static void main(String[] args) {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "gangnam";
        String pass = "gangnam";

        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,pass); // 커넥션정보는 원래 이런식으로 관리안한다.(보안때문에)
            System.out.println("con : " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if(con != null){
                try {
                    con.close();
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }

            }
        }

    }
}
