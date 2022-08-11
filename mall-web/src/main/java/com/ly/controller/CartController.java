package com.ly.controller;

import com.ly.pojo.User;
import com.ly.service.CartService;
import com.ly.vo.ShopCar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/10 23:51
 * @Description: TODO
 */
@Controller
public class CartController {

    @Resource
    private CartService cartService;

    @ResponseBody
    @PostMapping("/orderItem/create/{id}")
    public Map<String, Object> cart(@PathVariable("id") String productId, Long product_number, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        System.out.println(user);
        Map<String, Object> map = new HashMap<>(2) {
            @Override
            public Object put(String key, Object value) {
                return super.put(key, value);
            }
        };

        final boolean bool = cartService.create(user.getUserId() + "", productId, product_number);
        map.put("success", bool);

        return map;
    }


    /**
     * 实现购物车功能
     *
     * @param model
     * @return
     */
    @GetMapping("/cart")
    public String doCart(Model model, String searchValue, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return "/fore/productBuyCarPage";
        }
        model.addAttribute("searchValue", searchValue);
        List<ShopCar> cart = cartService.cart(user.getUserId());
        model.addAttribute("shopCarList", cart);


        model.addAttribute("orderItemTotal", cart.size());
        return "/fore/productBuyCarPage";
    }


    @DeleteMapping("/orderItem/{id}")
    @ResponseBody
    public Map<String, Object> doOrderItem(@PathVariable("id") Long id, HttpSession httpSession) {
        final Map<String, Object> map = new HashMap<>(2);
        final User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            map.put("success", false);
            return map;
        }

        final boolean delCart = cartService.delCart(id, user.getUserId());
        map.put("success", delCart);
        return map;
    }
}
