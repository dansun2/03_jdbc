package com.ohgiraffers.employee.view;

import com.ohgiraffers.employee.dto.EmpInsertDTO;
import com.ohgiraffers.employee.dto.EmployeeDTO;
import com.ohgiraffers.employee.service.EmployeeService;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeView {
    private static boolean state = true;

    // EmployeeService 클래스의 메소드를 호출하기 위해 객체를 생성함.
    private static EmployeeService employeeService = new EmployeeService();


    public static void run(){
        while (state){
            System.out.println("1. 사원 전체보기");
            System.out.println("2. 사원 이름으로 조회하기");
            System.out.println("3. 사원 정보 등록하기");
            System.out.println("4. 사원 정보 수정하기");
            System.out.print("화면 번호를 입력해주세요 : ");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine()); // 문자열을 int index에 넣기 위해 정수형으로 강제 변환해줌

            switch (index){
                case 1 :
                    employeeViewAll();
                    break;
                case 2 :
                    employeeFindByName();
                    break;
                case 3:
                    empInsert();
                    break;
                case 4:
                    empUpdate();
                    break;
            }
            System.out.print("종료를 하시겠습니까? 말해 (yes Or no) 오타x 소문자만 : ");
            String result = sc.nextLine();

            if(result.equals("yes")){
                state = false;
                sc.close();
            }
        }

    }

    // 현재 html의 화면을 암시하고 만든 것이다.
    // view는 사용자에게 데이터를 입력받고 서버에 전달하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.
    public static void employeeViewAll() {
        System.out.println("사원 정보 전체 조회");

        // 예외처리(비정상적인 종료 등)를 위해 try/catch구문 사용
        try {
            // 전체사원정보를 저장하기 위해 ArrayList생성해주고 변수명은 emps
            ArrayList emps = employeeService.employeeViewAll();
            System.out.println(emps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void employeeFindByName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사원의 이름을 입력하세요 : ");
        String name = sc.nextLine();
        EmployeeDTO emp = null;
        try {
            emp = employeeService.employeeFindByName(name);
            System.out.println(emp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void empInsert(){
        Scanner sc = new Scanner(System.in);
        EmpInsertDTO emp = new EmpInsertDTO();
        System.out.println("등록할 사원의 정보를 입력해주세요 ");
        System.out.print("사원의 번호를 입력해주세요 : ");
        emp.setEmpId(sc.nextLine());
        System.out.print("사원의 이름을 입력해주세요 : ");
        emp.setEmpName(sc.nextLine());
        System.out.print("사원의 주민번호를 입력해주세요 : ");
        emp.setEmpNo(sc.nextLine());
        System.out.print("사원의 직급을 입력해주세요 (대표,부사장,부장,차장,과장,대리,사원) : ");
        emp.setJobCode(sc.nextLine());
        System.out.print("사원의 급여를 입력해주세요 (S1,S2,S3,S4,S5,S6) : ");
        emp.setSalLevel(sc.nextLine());

        try {
            String result = employeeService.empInsert(emp);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 업데이트
    public static void empUpdate(){
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 사원번호를 입력하세요 : ");
        String index = sc.nextLine();
        EmployeeDTO emp = employeeService.empFindById(index);
        
        if(emp == null){
            System.out.println("변경할 사원이 존재하지 않습니다.");
            return;
        }
        System.out.println(emp);
        System.out.print("변경할 이름을 입력해주세요.");
        String name = sc.nextLine();
        // 만약에 n개를 변경한다 하면 DTO를 만들어서 해주면 된다.

        try {
            EmployeeDTO modifyEmp = employeeService.empModify(name,index); //DTO면 name대신 DTO전
            System.out.println(modifyEmp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
