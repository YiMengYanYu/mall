package com.ly.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {

    @TableId
    private long adminId;
    private String adminName;
    private String adminNickname;
    private String adminPassword;
    private String adminProfilePictureSrc;


}
