package com.ly.service;

import com.ly.pojo.Productimage;

/**
 * @author YiMeng
 */
public interface ProductimageService {


    Boolean delImgById(Integer id);

    Boolean putProductimage(Productimage productimage);
    Boolean insertProductimage(Productimage productimage);


}
