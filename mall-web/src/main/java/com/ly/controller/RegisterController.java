package com.ly.controller;

import com.ly.constants.keyconstants.KeyConstants;
import com.ly.constants.requstconstnts.RequstConstnts;
import com.ly.constants.viewconstants.ViewConstants;
import com.ly.pojo.Address;
import com.ly.pojo.User;
import com.ly.service.AddressService;
import com.ly.service.UserService;
import com.ly.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author YiMeng
 * @DateTime: 2022/8/4 9:40
 * @Description: TODO
 */
@Controller
@Slf4j
public class RegisterController {


    @Resource
    AddressService addressService;
    @Resource
    UserService userService;


    @GetMapping("/login")
    public String doLogin() {

        return "/fore/loginPage";
    }

    @GetMapping(RequstConstnts.REG_HTML)
    public String toReg(Model model) {
        List<Address> address = addressService.getAddressByaddressRegionId(null);
        List<Address> cityList = addressService.getAddressByaddressRegionId(address.get(0).getAddressAreaId());
        List<Address> districtList = addressService.getAddressByaddressRegionId(cityList.get(0).getAddressAreaId());
        model.addAttribute("addressList", address);
        model.addAttribute("cityList", cityList);
        model.addAttribute("districtList", districtList);
        log.info(address.toString());
        return ViewConstants.FORE_REGISTER;
    }

    @ResponseBody
    @GetMapping(RequstConstnts.ADDRESS_ID)
    public Map<String, Object> getAddress(@PathVariable("id") String id) {
        log.info("请求地址下拉框" + id);
        Map<String, Object> map = new Hashtable<>(16);
        List<Address> addressList = addressService.getAddressByaddressRegionId(id);
        List<Address> childAddressList = addressService.getAddressByaddressRegionId(addressList.get(0).getAddressAreaId());
        map.put("addressList", addressList);
        map.put("childAddressList", childAddressList);
        map.put("success", true);

        return map;
    }

    @ResponseBody
    @PostMapping("/register/doRegister")
    public Map<String, Boolean> doRegister(User user) {
        System.out.println(user);
        Boolean register = userService.register(user);
        Map<String, Boolean> map = new HashMap<>(2);
        map.put("success", register);
        return map;
    }


}
