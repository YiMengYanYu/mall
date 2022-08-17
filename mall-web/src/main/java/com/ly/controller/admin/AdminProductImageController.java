package com.ly.controller.admin;


import com.ly.service.ProductimageService;
import com.ly.utils.FileUpload;
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
import java.util.List;
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
            map.put("success", productimageService.delImgById(id));
            Files.delete(Path.of(realPath));

            return map;
        } catch (IOException e) {
            map.put("success", false);
            return map;

        }

    }

    @ResponseBody
    @PostMapping("/uploadProductImage")
    public Map<String, Object> uploadProductImage(MultipartFile file, String imageType, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<>(2);
        if ("single".equals(imageType)) {

            final List<String> list = FileUpload.saveImgUpload(httpSession, "/productSinglePicture", file);
            map.put("fileName", list.get(0));
            map.put("success", true);
        } else {
            final List<String> list = FileUpload.saveImgUpload(httpSession, "/productDetailsPicture", file);
            map.put("fileName", list.get(0));
            map.put("success", true);
        }
        return map;
    }
}
