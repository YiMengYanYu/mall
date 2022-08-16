package com.ly.controller.admin;


import com.ly.service.ProductimageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YiMeng
 * @DateTime: 2022/8/16 15:11
 * @Description: TODO
 */
@RequestMapping("/admin")
@Controller
public class AdminProductImageController {
    @Resource
    private ProductimageService productimageService;

    public static void main(String[] args) {
        String img = "/mall/res/images/item/productSinglePicture/494081e5-fdca-4af6-bf4d-c6854c0ee02d.jpg";


    }

    @ResponseBody
    @DeleteMapping("/productImage/{id}")
    public Map<String, Object> delImg(@PathVariable("id") Integer id, String src, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>(2);
        String decode = null;

        try {
            decode = URLDecoder.decode(src, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        decode = decode.substring(5);
        String realPath = httpSession.getServletContext().getRealPath(decode);
        try {
            Files.delete(Path.of(realPath));
            map.put("success", productimageService.delImgById(id));
            return map;
        } catch (IOException e) {
            map.put("success", false);
            return map;

        }

    }

    @PostMapping("/uploadProductImage")
    public void uploadProductImage(MultipartFile file, String imageType) {
        
    }
}
