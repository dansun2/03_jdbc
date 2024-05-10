package com.javaeagles.section01;

import com.javaeagles.section01.controller.Pcontorller;
import com.javaeagles.section01.dto.PhoneDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Pcontorller pcontorller = new Pcontorller();

        //데이터를 저장할 ArrayList생성 (PhoneDTO 클래스에서 가져오는데 변수명은 members
        ArrayList<PhoneDTO> members = new ArrayList<PhoneDTO>();
        int num = 0; // 몇명이 등록되었는지 체크하기 위한 숫자

        String result = "";


        while (true){
            System.out.println("=====전화번호부=====");
            System.out.println("1. 전화번호 등록");
            System.out.println("2. 전화번호 수정");
            System.out.println("3. 전화번호 삭제");
            System.out.println("4. 전화번호 전체조회");
            System.out.println("5. 전화번호 상세조회");
            System.out.print("원하시는 메뉴를 선택하세요 : ");

            int input = sc.nextInt(); // 목차를 선택하기 위해 번호를 입력받음.
            sc.nextLine(); // 줄까지 포함
            switch (input){ // 입력받은 번호에 따라서 case 번호 실행
                case 1 :
                    int keepgoing = 1;
                    while(keepgoing == 1){ // 추가입력을 위해 while문을 사용하고 keepgoing의 기본값을 1로 설정
                        System.out.print("이름을 입력하세요. : ");
                        String name = sc.nextLine();

                        System.out.print("전화번호를 입력하세요 : ");
                        String phone = sc.nextLine();

                        System.out.print("이메일을 입력하세요 : ");
                        String email = sc.nextLine();
                        // 아무것도 입력안했을때 넘기려면 뭘 넣어야 될까

                        System.out.print("메모를 입력하세요 : ");
                        String memo = sc.nextLine();
                        // 아무것도 입력안했을때 넘기려면 뭘 넣어야 될까

//                        System.out.print("그룹을 지정하세요 ( 1.미지정 / 2.가족 / 3.회사 / 4.친구 ) : ");
//                        int group = Integer.parseInt(sc.nextLine());

                        members.add(new PhoneDTO(name, phone, email, memo));
                        num++; //회원추가가 완료되면 위에서 만들었던 members 리스트에 넣고 카운팅함

                        System.out.println(members); // 입력받은 정보를 확인시켜주기 위해 출력해줌
                        System.out.println("전화번호부에 등록되었습니다.");
                        System.out.print("계속 등록하시려면 1번을 누르세요. ");
                        keepgoing = sc.nextInt(); // 1번이면 위로 올라가서 while문 다시 실행함
                        sc.nextLine();

                    } // 컨트롤러 넘겨
                    result = members.toString(); // 받은 고객DB를 주소록에 전달해줘 (그 안에서 꺼내쓰게)
                    break;
 
                case 2 :
                    // 수정
                    // 수정하고 싶은 이름 입력
                    // 입력 받은 이름을 조회하고 존재하는 확인
                    // 존재하면 true 수정할 항목선택 존재하지않으면 false
                    // 컨트롤러 넘겨
                    break;
                case 3 :
                    // 삭제
                    // 삭제하고 싶은 이름 입력
                    // 입력 받은 이름을 조회하고 존재하는지 확인
                    // 존재하면 true 전체삭제 존재하지 않으면 false
                    // 컨트롤 넘겨
                case 4 :
                    // 전체조회
                    // 등록된 번호 모두 조회
                    // 넘겨
                case 5 :
                    // 상세조회
                    // 이름을 입력 받아 존재하는지 확인
                    // 있으면 모든 정보 조회 없으면 false
                    // 넘겨
                default:
                    System.out.println("잘못 입력 됐습니다.");
                    break;

            }
            System.out.println("종료하시겠습니까?");
        }



    }
}
