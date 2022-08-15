package com.ly.utils;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 22:00
 * @Description: TODO
 */
public class PasswordUtils {

    private static final int oddValueAdded=1;
    private static final int evenValueAdded=-1;


    /**
     * 加密
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        byte[] bytes = password.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if(i%2==0){
                bytes[i]+=evenValueAdded;
            }else {
                bytes[i]+=oddValueAdded;
            }
        }
        return new String(bytes);
    }

    /**
     * 解密
     * @param code
     * @return
     */
    public static String decode(String code) {
        byte[] bytes = code.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if(i%2==0){
                bytes[i]-=evenValueAdded;
            }else {
                bytes[i]-=oddValueAdded;
            }
        }
        return new String(bytes);
    }


}
