package com.ly.controller;

import com.ly.constants.requstconstnts.RequstConstnts;
import com.ly.constants.viewconstants.ViewConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YiMeng
 * @DateTime: 2022/8/4 9:40
 * @Description: TODO
 */
@Controller
public class RegisterController {

    @GetMapping(RequstConstnts.REG_HTML)
    public String toReg() {
        return ViewConstants.FORE_REGISTER;
    }


}
