package com.ly.controller;

import com.ly.pojo.Address;
import com.ly.pojo.User;
import com.ly.service.AddressService;
import com.ly.service.UserService;
import com.ly.utils.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/13 22:55
 * @Description: TODO
 */
@Controller
public class UserDetailsController {

    @Resource
    AddressService addressService;

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/user/uploadUserHeadImage")
    public Map<String, Object> uploadUserHeadImage(@RequestParam(value = "file") MultipartFile file, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>(2);
        User suser = (User) httpSession.getAttribute("user");
        User user = new User();
        List<String> list = FileUpload.saveImgUpload(httpSession, "/userProfilePicture", file);
        user.setUserProfilePictureSrc(list.get(0));
        user.setUserId(suser.getUserId());
        map.put("success", userService.updateImg(user));
        map.put("fileName", list.get(0));
        httpSession.setAttribute("user", userService.getUserById(user.getUserId()));

        return map;
    }

    @PostMapping("/user/update")
    public String userUpdate(User user, HttpSession httpSession) {
        User suser = (User) httpSession.getAttribute("user");

        user.setUserProfilePictureSrc(null);
        user.setUserId(suser.getUserId());
        userService.update(user);
        httpSession.invalidate();
        return "/fore/loginPage";
    }

    @GetMapping("/userDetails")
    public String doUserDetails(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return "/fore/loginPage";
        }
        String parentByaddressAreaId = addressService.getParentByaddressAreaId(user.getUserHomePlace());
        model.addAttribute("addressId", parentByaddressAreaId);
        model.addAttribute("cityAddressId", user.getUserHomePlace());
        model.addAttribute("districtAddressId", user.getUserAddress());
        model.addAttribute("user", user);

        List<Address> address = addressService.getAddressByaddressRegionId(null);
        List<Address> cityList = addressService.getAddressByaddressRegionId(parentByaddressAreaId);
        List<Address> districtList = addressService.getAddressByaddressRegionId(user.getUserHomePlace());
        model.addAttribute("addressList", address);
        model.addAttribute("cityList", cityList);
        model.addAttribute("districtList", districtList);
        return "/fore/userDetails";
    }


}
