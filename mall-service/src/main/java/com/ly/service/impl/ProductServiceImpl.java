package com.ly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mapper.*;
import com.ly.pojo.*;
import com.ly.service.ProductService;
import com.ly.utils.PageUtil;
import com.ly.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 19:15
 * @Description: TODO
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ProductimageMapper productimageMapper;
    @Resource
    private ProductorderitemMapper productorderitemMapper;
    @Resource
    private ReviewMapper reviewMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public List<Product> getProductAll() {
        List<Product> getProductAll = redisUtil.getCacheList("getProductAll", Product.class);
        if (getProductAll == null) {
            getProductAll = productMapper.selectList(null);
            redisUtil.setCacheObject("getProductAll", getProductAll, 60, TimeUnit.SECONDS);
        }
        return getProductAll;
    }

    @Override
    public List<Product> getProductByProductCategoryId(String id) {
        List<Product> productList = redisUtil.getCacheList("getProductByProductCategoryId" + id, Product.class);
        if (productList == null) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productCategoryId", id);
            productList = productMapper.selectList(queryWrapper);
            redisUtil.setCacheObject("getProductByProductCategoryId" + id, productList, 60, TimeUnit.SECONDS);
        }

        return productList;
    }

    @Override
    public List<Product> getProductIsEnabledEq2() {
        List<Product> getProductAll = redisUtil.getCacheList("getProductAll", Product.class);
        if (getProductAll == null) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productIsEnabled", 2);
            getProductAll = productMapper.selectList(queryWrapper);
            redisUtil.setCacheObject("getProductAll", getProductAll, 60, TimeUnit.SECONDS);
        }
        return getProductAll;
    }

    @Override
    public List<Product> getProductByProductName(String productName) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("productIsEnabled", 1);
        queryWrapper.like("productName", productName);
        List<Product> productList = redisUtil.getCacheList("getProductByProductName" + productName, Product.class);
        if (productList == null) {
            productList = productMapper.selectList(queryWrapper);
            for (Product product : productList) {
                QueryWrapper<Review> reviewQueryWrapper = new QueryWrapper<>();
                reviewQueryWrapper.eq("reviewProductId", product.getProductId());
                product.setProductReviewCount(reviewMapper.selectCount(reviewQueryWrapper));
                QueryWrapper<Productorderitem> itemqw = new QueryWrapper<>();
                itemqw.eq("productorderitemProductId", product.getProductId());
                product.setProductSaleCount(productorderitemMapper.selectCount(itemqw));
                QueryWrapper<Category> cqw = new QueryWrapper<>();
                cqw.eq("categoryId", product.getProductCategoryId());
                Category category = categoryMapper.selectOne(cqw);
                product.setProductCategory(category);
                QueryWrapper<Productimage> qw = new QueryWrapper<>();
                qw.eq("productimageProductId", product.getProductId());
                qw.eq("productimageType", 0);
                product.setSingleProductImageList(productimageMapper.selectList(qw));
            }
            redisUtil.setCacheObject("getProductByProductName" + productName, productList, 60, TimeUnit.SECONDS);
        }

        return productList;
    }


    @Override
    public List<Product> getProductSoft(String startIndex, String endIndex, String orderBy, String productName, boolean isDesc, String categoryId) {

        List<Product> productList = redisUtil.getCacheList("getProductSoft" + productName + categoryId + orderBy + startIndex + endIndex + isDesc, Product.class);
        if (productList == null) {

            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("productIsEnabled", 1);
            if (ObjectUtils.isEmpty(categoryId)) {
                queryWrapper.like("productName", productName);
                if (orderBy.equals("productSaleCount")) {
                    String sql = "(SELECT \t (count(`productorderitemNumber`)) FROM `productorderitem`\n" +
                            "WHERE `productorderitemProductId`=productId)";
                    queryWrapper.orderBy(true, !isDesc, sql);

                } else {
                    queryWrapper.orderBy(true, !isDesc, orderBy);
                }
                queryWrapper.last("LIMIT " + startIndex + "," + endIndex);
            } else {
                queryWrapper.eq("productCategoryId", categoryId);
                if (!ObjectUtils.isEmpty(orderBy)) {
                    if (orderBy.equals("productSaleCount")) {

                        String sql = "(SELECT \t (count(`productorderitemNumber`)) FROM `productorderitem`\n" +
                                "WHERE `productorderitemProductId`=productId)";
                        queryWrapper.orderBy(true, !isDesc, sql);
                    } else {
                        queryWrapper.orderBy(true, !isDesc, orderBy);
                    }
                }

            }


            productList = productMapper.selectList(queryWrapper);

            for (Product product : productList) {
                QueryWrapper<Review> reviewQueryWrapper = new QueryWrapper<>();
                reviewQueryWrapper.eq("reviewProductId", product.getProductId());
                product.setProductReviewCount(reviewMapper.selectCount(reviewQueryWrapper));
                QueryWrapper<Productorderitem> itemqw = new QueryWrapper<>();
                itemqw.eq("productorderitemProductId", product.getProductId());
                product.setProductSaleCount(productorderitemMapper.selectCount(itemqw));
                QueryWrapper<Category> cqw = new QueryWrapper<>();
                cqw.eq("categoryId", product.getProductCategoryId());
                Category category = categoryMapper.selectOne(cqw);
                product.setProductCategory(category);
                QueryWrapper<Productimage> qw = new QueryWrapper<>();
                qw.eq("productimageProductId", product.getProductId());
                qw.eq("productimageType", 0);
                product.setSingleProductImageList(productimageMapper.selectList(qw));
            }
            redisUtil.setCacheObject("getProductSoft" + productName + categoryId + orderBy + startIndex + endIndex + isDesc, productList, 60, TimeUnit.SECONDS);
        }

        return productList;
    }

    @Override
    public Long getProductCount() {
        return productMapper.getProductCount();
    }

    @Override
    public PageUtil<Product> getProduct(String productName, Integer categoryId, Double productSalePrice, Double productPrice, Integer[] productIsEnabledArray, String orderBy, Boolean isDesc, Integer startIndex, Integer pageSize) {
        PageHelper.startPage(startIndex + 1, pageSize);
        List<Product> product = productMapper.getProduct(productName, categoryId, productSalePrice, productPrice, productIsEnabledArray, orderBy, isDesc);
        PageInfo pageInfo = new PageInfo(product);
        PageUtil<Product> pageUtil =new PageUtil<>();
        pageUtil.setList(product);
        pageUtil.setIndex(startIndex);
        pageUtil.setTotalPage(pageInfo.getPages());
        pageUtil.setHasPrev(pageInfo.isHasPreviousPage());
        pageUtil.setHasNext(pageInfo.isHasNextPage());
        return pageUtil;
    }

    @Override
    public Product getProductByProductId(String productId) {
        //Product products  = redisUtil.getCache("getProductByProductId", Product.class);
        // if (products==null) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("productId", productId);
        Product products = productMapper.selectOne(productQueryWrapper);

        final QueryWrapper<Productorderitem> select = new QueryWrapper<Productorderitem>().select(" SELECT(SUM(`productorderitemNumber`))FROM`productorderitem`WHERE`productorderitemProductId`=productId");

        products.setProductSaleCount(productorderitemMapper.getProductorderitemSumBypProductorderitemProductId(Long.valueOf(productId)));
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("categoryId", products.getProductId());
        QueryWrapper<Review> reviewQueryWrapper = new QueryWrapper<>();
        reviewQueryWrapper.eq("reviewProductId", products.getProductId());
        List<Review> reviews = reviewMapper.selectList(reviewQueryWrapper);
        for (Review review : reviews) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("userId", review.getReviewUserId());
            review.setReviewUser(userMapper.selectOne(userQueryWrapper));
        }
        products.setReviewList(reviews);
        products.setProductReviewCount(reviewMapper.selectCount(new QueryWrapper<Review>().eq("reviewProductId", products.getProductId())));
        QueryWrapper<Productimage> productimageQueryWrapper = new QueryWrapper<>();
        productimageQueryWrapper.eq("productimageType", 0);
        productimageQueryWrapper.eq("productimageProductId", products.getProductId());
        products.setSingleProductImageList(productimageMapper.selectList(productimageQueryWrapper));
        products.setDetailProductImageList(productimageMapper.selectList(new QueryWrapper<Productimage>().eq("productimageType", 1).eq("productimageProductId", products.getProductId())));
        products.setProductCategory(categoryMapper.selectOne(queryWrapper));
        //     redisUtil.setCacheObject("getProductByProductId", products, 60, TimeUnit.SECONDS);
        //  }

        return products;
    }

    @Override
    public List<Product> getLoveProductList() {

        Long count = productMapper.selectCount(null);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        Random random = new Random();
        int i = random.nextInt(Math.toIntExact(count) - 4);

        queryWrapper.last("LIMIT " + i + "," + 3);
        List<Product> products = productMapper.selectList(queryWrapper);
        for (Product product : products) {
            QueryWrapper<Productimage> productimageQueryWrapper = new QueryWrapper<>();
            productimageQueryWrapper.eq("productimageProductId", product.getProductId()).eq("productimageType", 0);
            List<Productimage> productimages = productimageMapper.selectList(productimageQueryWrapper);

            product.setSingleProductImageList(productimages);
        }
        return products;
    }


}
