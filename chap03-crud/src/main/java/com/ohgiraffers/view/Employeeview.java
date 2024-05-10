package com.ohgiraffers.view;

import java.util.Scanner;

public class Employeeview {
    private static boolean state = true;


    public static void run(){
        while(state) {
            System.out.print("화면 번호를 입력해주세요 : ");
            System.out.println("1. 화면 전체보기");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine()); // 왜 굳이 integer로? nextInt쓰면 정수형으로 안되나?

            switch (index) {
                case 1:
                    employeeViewAll();
                    break;
            }
            System.out.print("종료를 하시겠습니까? 말해 (yes or no) 오타X 소문자만 : ");
            String result = sc.nextLine();
            if(result.equals("yes")){
                state = false;
            }
        }
    }


    // 현재 html의 화면을 암시하고 만든 것이다.
    // view는 사용자에게 데이터를 입력받고 서버에 요청을 하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.
    public static void employeeViewAll(){
        System.out.println("사원 정보 전체 조회");

    }


}
