package com.ohgiraffers.employee.service;

import com.ohgiraffers.employee.dao.EmployeeRepository;
import com.ohgiraffers.employee.dto.EmpInsertDTO;
import com.ohgiraffers.employee.dto.EmployeeDTO;

import java.util.ArrayList;

public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    // service
    // 비즈니스의 도메인과 관련된 역할을 수행한다.
    // 데이터베이스와 연결된 작업을 수행한다.
    public ArrayList employeeViewAll() throws Exception {
        ArrayList employees = employeeRepository.employeeViewAll();

        if(employees == null){
            throw new Exception("사원정보 조회실패");
        }

        return employees;
    }

    public EmployeeDTO employeeFineByName(String name) throws Exception {
        //벨리데이션 체크? 전제조건으로 이름값이 존재하는지 확인
        //name이 입력되지 않은 경우
        if(name == null && !name.equals("")){
            return null;
        }

        EmployeeDTO emp = employeeRepository.employeeFindByName(name);

        if(emp == null){
            throw new Exception("사원정보 조회실패");
        }
        return emp;
    }

    public String empInsert(EmpInsertDTO emp) throws Exception {
        // 서비스는 아래와 같이 우리의 비즈니스 로직에 맞는 유효성을 검사한다.
        // 아래는 사원의 번호가 중복되는 것을 확인하고 만약 중복이라면 등록을 취소해야 한다.
        EmployeeDTO findEmp = employeeRepository.empFindById(emp.getEmpId());
        if(findEmp != null){
            throw new Exception("중복회원");
        }

        String result = employeeRepository.empInsert(emp);
    }
}
