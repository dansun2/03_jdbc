package com.javaeagles.phone.service;

import com.javaeagles.phone.dao.PhoneRepository;
import com.javaeagles.phone.dto.PhoneDTO;

import java.util.ArrayList;
import java.util.Objects;

public class PhoneService {
    private static PhoneRepository phoneRepository;

    public PhoneService() {
        this.phoneRepository = new PhoneRepository();
    }

    // service
    // 비즈니스의 도메인과 관련된 역할을 수행한다.
    // 데이터베이스와 연결된 작업을 수행한다.
    public ArrayList phoneViewAll() throws Exception {
        ArrayList phone = phoneRepository.phoneViewAll();

        if(phone == null){
            throw new Exception("정보 조회실패");
        }

        return phone;
    }

    public PhoneDTO phoneFindByName(String name) throws Exception {
        // name이 입력되지 않은 경우
        if(name == null || name.equals("")){
            return null;
        }

        PhoneDTO ph = phoneRepository.phoneFindByName(name);

        if(ph == null){
            throw new Exception("정보 조회실패");
        }
        return ph;
    }

    public String phoneInsert(PhoneDTO ph) throws Exception {
        // 서비스는 아래와 같이 우리의 비즈니스 로직에 맞는 유효성을 검사한다.
        // 아래는 사원의 번호가 중복되는 것을 확인하고 만약 중복이라면 등록을 취소해야한다.
        PhoneDTO findPh = phoneRepository.phoneFindById(String.valueOf(ph.getUserCode()));
//        System.out.println(findPh);
        // 문자열로 강제 형변환 해주는 것.

        if(findPh != null){
            throw new Exception("중복회원");
        }

        int result = phoneRepository.phoneInsert(ph);

        if(result < 0){
            throw new Exception("등록실패");
        }

        return (result > 0) ? "등록성공" : "등록실패";
    }

    public PhoneDTO phoneFindById(String index) {
        PhoneDTO findPh = phoneRepository.phoneFindById(index);
        if(findPh != null){
            return findPh;
        }else{
            return null;
        }
    }

    public PhoneDTO phoneModify(String name, String index) throws Exception {
        if(name.equals("") || name == null){
            throw new Exception("빈값 입력");
        }

        int result = phoneRepository.phoneModify(name,index);
        if(result < 0){
            throw new Exception("변경실패");
        }

        PhoneDTO modifyPh = phoneRepository.phoneFindById(index);
        return modifyPh;
    }
    public PhoneDTO phoneDelete (String name) throws Exception {
        PhoneDTO findPH = phoneRepository.phoneFindById(name);
        if(findPH != null) {
            throw new Exception("삭제할 전화번호가 존재하지 않습니다.");
        }

        int result = phoneRepository.phoneDelete(name);
        if(result < 0 ) {
            throw new Exception("전화번호 삭제 실패");
        }

        PhoneDTO deletePh = phoneRepository.phoneFindById(name);
        return deletePh;
    }
}