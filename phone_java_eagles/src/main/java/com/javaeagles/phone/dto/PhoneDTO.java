package com.javaeagles.phone.dto;

public class PhoneDTO {

    private int userCode;
    private String userName;
    private String userEmail;
    private String userMemo;
    private String userGroup;
    private String phoneCode;
    private String phoneName;
    private String phone;

    public PhoneDTO() {
    }

    public PhoneDTO(int userCode, String userName, String userEmail, String userMemo, String userGroup, String phoneCode, String phoneName, String phone) {
        this.userCode = userCode;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMemo = userMemo;
        this.userGroup = userGroup;
        this.phoneCode = phoneCode;
        this.phoneName = phoneName;
        this.phone = phone;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMemo() {
        return userMemo;
    }

    public void setUserMemo(String userMemo) {
        this.userMemo = userMemo;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return " 단축번호 = " + userCode +
                " 이름 = '" + userName + '\'' +
                " email = '" + userEmail + '\'' +
                " Memo = '" + userMemo + '\'' +
                " Group = '" + userGroup + '\'' +
                " phone = '" + phone + '\n';
        //", phoneCode = '" + phoneCode + '\'' +
        //", phoneName = '" + phoneName + '\'' +
    }
}