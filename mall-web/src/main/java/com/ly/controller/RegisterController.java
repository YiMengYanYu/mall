package com.ly.controller;

import com.ly.constants.keyconstants.KeyConstants;
import com.ly.constants.requstconstnts.RequstConstnts;
import com.ly.constants.viewconstants.ViewConstants;
import com.ly.pojo.Address;
import com.ly.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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

    @GetMapping(RequstConstnts.REG_HTML)
    public String toReg(Model model) {
        List<Address> address = addressService.getAddressByaddressRegionId(null);
        model.addAttribute(KeyConstants.ADDRESS_LIST, address);
        log.info(address.toString());
        return ViewConstants.FORE_REGISTER;
    }

    @ResponseBody
    @GetMapping(RequstConstnts.ADDRESS_ID)
    public Map<String, Object> getAddress(@PathVariable("id") String id) {
        log.info("请求地址下拉框" + id);
        Map<String, Object> map = new Hashtable<>(16);
        List<Address> addressList= addressService.getAddressByaddressRegionId(id);
        List<Address> childAddressList= addressService.getAddressByaddressRegionId(addressList.get(0).getAddressAreaId());
        map.put("addressList", addressList);
        map.put("childAddressList", childAddressList);
        map.put("success", true);

        return map;

    }

}
