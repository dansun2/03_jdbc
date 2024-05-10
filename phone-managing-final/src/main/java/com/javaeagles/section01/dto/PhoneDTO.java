package com.javaeagles.section01.dto;

public class PhoneDTO {
    private String name;
    private String phone;
    private String memo;
    private String email;

    public PhoneDTO(String name, String phone, String email, String memo) {
        this.name = name;
        this.phone = phone;
        this.memo = memo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "성함 = " + name + '\n' +
                "전화번호 = " + phone + '\n' +
                "이메일 = " + email + '\n' +
                "메모 = " + memo;
    }
}
