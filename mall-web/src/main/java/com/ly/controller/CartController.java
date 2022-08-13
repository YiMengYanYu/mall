package com.ly.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.pojo.Address;
import com.ly.pojo.User;
import com.ly.service.AddressService;
import com.ly.service.CartService;
import com.ly.service.CategoryService;
import com.ly.vo.OrderVo;
import com.ly.vo.ShopCar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @Resource
    private CategoryService categoryService;

    @Resource
    private AddressService addressService;

    @Resource
    private ObjectMapper objectMapper;

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


        model.addAttribute("categoryList", categoryService.getCategoryAllImpl2());


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


    @PutMapping("/orderItem")
    @ResponseBody
    public Map<String, Object> orderItem(HttpSession httpSession, String orderItemMap) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        map.put("orderItemIDArray", orderItemMap);
        return map;
    }

    @GetMapping("/order/create/byCart")
    public String createByCart(String order_item_list, Model model, String detailsAddress, String order_post, String order_receiver, String order_phone, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        if (user == null) {
//            return "/fore/productBuyCarPage";
//        }
        List<Address> address = addressService.getAddressByaddressRegionId(null);
        List<Address> cityList = addressService.getAddressByaddressRegionId(address.get(0).getAddressAreaId());
        List<Address> districtList = addressService.getAddressByaddressRegionId(cityList.get(0).getAddressAreaId());
        model.addAttribute("addressList", address);
        model.addAttribute("cityList", cityList);
        model.addAttribute("districtList", districtList);
        model.addAttribute("detailsAddress", detailsAddress);
        model.addAttribute("order_post", order_post);
        model.addAttribute("order_receiver", order_receiver);
        model.addAttribute("order_phone", order_phone);
        String uri = "";
        try {
            uri = URLDecoder.decode(order_item_list, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Long> map = null;
        try {
            map = objectMapper.readValue(uri, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<ShopCar> cartAllByMap = cartService.getCartAllByMap(map);
        model.addAttribute("shopCarList", cartAllByMap);
        Double num = 0D;
        for (ShopCar shopCar : cartAllByMap) {
            num += shopCar.getProduct().getProductPrice() * 100 * shopCar.getNumber() / 100;
        }

        model.addAttribute("orderTotalPrice", num);
        return "/fore/productBuyPage";
    }

    @PostMapping("/order")
    public void order(OrderVo orderVo) {


    }

    @PostMapping("/order/list")
    public void orderList(OrderVo orderVo) {

        System.err.println(orderVo);
    }
}
