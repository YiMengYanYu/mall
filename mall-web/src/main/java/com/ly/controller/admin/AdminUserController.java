package com.ly.controller.admin;

import com.ly.pojo.User;
import com.ly.service.UserService;
import com.ly.utils.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 21:28
 * @Description: TODO
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Resource
    private UserService userService;

    @GetMapping("/user")
    public String doUser(Map map) {
        PageUtil<User> userPage = userService.getUserPage(0, 10, "", Arrays.asList(0, 1), null, null);
        map.put("userList", userPage.getList());
        map.put("pageUtil", userPage);
        return "/admin/userManagePage";
    }

    @ResponseBody
    @GetMapping("/user/{startIndex}/{endIndex}")
    public Map<String, Object> getUserPage(@PathVariable("startIndex") Integer startIndex, @PathVariable("endIndex") Integer endIndex, String userName,  Integer[] user_gender_array, Boolean isDesc, String orderBy) {
        PageUtil<User> userPage = userService.getUserPage(startIndex, endIndex, userName, user_gender_array==null || user_gender_array.length<1 || user_gender_array[0]==null ? null : Arrays.asList(user_gender_array), isDesc, orderBy);
        Map<String, Object> map = new HashMap<>(2);
        map.put("userList", userPage.getList());
        map.put("pageUtil", userPage);
        return map;
    }
}
