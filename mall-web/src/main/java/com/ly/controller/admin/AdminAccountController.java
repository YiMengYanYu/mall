package com.ly.controller.admin;

import com.ly.pojo.Admin;
import com.ly.service.AdminService;
import com.ly.utils.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/17 22:38
 * @Description: TODO
 */
@RequestMapping("/admin")
@Controller
public class AdminAccountController {

    @Resource
    private AdminService adminService;

    @GetMapping("/account")
    public String doAccount(HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("admin");
        return "/admin/accountManagePage";
    }

    @ResponseBody
    @PostMapping("/uploadAdminHeadImage")
    public Map<String, Object> uploadAdminHeadImage(HttpSession httpSession, MultipartFile file) {
        Map<String, Object> map = new HashMap<>(2);
        List<String> list = FileUpload.saveImgUpload(httpSession, "/adminProfilePicture", file);
        map.put("fileName", list.get(0));
        map.put("success", true);
        return map;
    }

    @ResponseBody
    @PutMapping("/account/4")
    public Map<String, Object> putAccount(String adminNickname, String adminProfilePictureSrc, String adminPassword, String adminNewPassword, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>(2);
        Admin adminhttpSession = (Admin) httpSession.getAttribute("admin");
        Admin admin = new Admin();
        admin.setAdminId(adminhttpSession.getAdminId());
        if (!ObjectUtils.isEmpty(adminProfilePictureSrc)) {
            admin.setAdminProfilePictureSrc(adminProfilePictureSrc);
        }
        admin.setAdminNickname(adminNickname);
        if (!ObjectUtils.isEmpty(adminPassword) || !ObjectUtils.isEmpty(adminNewPassword)) {
            if (adminPassword.equals(adminhttpSession.getAdminPassword())) {
                admin.setAdminPassword(adminNewPassword);
            } else {
                map.put("success", false);
                map.put("message", "密码错误");
                return map;
            }
        }
        String adminName = adminhttpSession.getAdminName();
        httpSession.setAttribute("admin",adminService.getAdminByUserName(adminName));
        map.put("success", adminService.updateAdmin(admin));
        return map;
    }
}
