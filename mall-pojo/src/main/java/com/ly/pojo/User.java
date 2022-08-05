package com.ly.pojo;


import lombok.Data;

@Data
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




}
