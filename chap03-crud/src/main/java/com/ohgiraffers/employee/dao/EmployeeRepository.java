package com.ohgiraffers.employee.dao;
/* repository란?
* 데이터를 등록하고 수정하고 삭제하고 조회하는 등의
* 데이터베이스와 연결되어 동작하는 로직을 수행하는 계층이다.
 */

import com.ohgiraffers.common.JDBCTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class EmployeeRepository {
    private Properties prop = new Properties(); // properties는 sql 설정파일
    private Connection con = null;

    public EmployeeRepository() {
        try {
            this.prop.load(new FileReader("src/main/java/com/ohgiraffers/employee/mapper/employee-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList employeeViewAll(){
        String query = prop.getProperty("employeeAll");
        con = getConnection();
    }
}
