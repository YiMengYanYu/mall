package com.ly.pojo;



public class User {

  private long userId;
  private String userName;
  private String userNickName;
  private String userPassword;
  private String userRealname;
  private long userGender;
  private java.sql.Date userBirthday;
  private String userAddress;
  private String userHomePlace;
  private String userProfilePictureSrc;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserNickName() {
    return userNickName;
  }

  public void setUserNickName(String userNickName) {
    this.userNickName = userNickName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserRealname() {
    return userRealname;
  }

  public void setUserRealname(String userRealname) {
    this.userRealname = userRealname;
  }


  public long getUserGender() {
    return userGender;
  }

  public void setUserGender(long userGender) {
    this.userGender = userGender;
  }


  public java.sql.Date getUserBirthday() {
    return userBirthday;
  }

  public void setUserBirthday(java.sql.Date userBirthday) {
    this.userBirthday = userBirthday;
  }


  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }


  public String getUserHomePlace() {
    return userHomePlace;
  }

  public void setUserHomePlace(String userHomePlace) {
    this.userHomePlace = userHomePlace;
  }


  public String getUserProfilePictureSrc() {
    return userProfilePictureSrc;
  }

  public void setUserProfilePictureSrc(String userProfilePictureSrc) {
    this.userProfilePictureSrc = userProfilePictureSrc;
  }

}
