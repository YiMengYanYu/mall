package com.ly.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author YiMeng
 */
@Data
public class User {

    @TableId("userId")
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
    private String salt;



}
