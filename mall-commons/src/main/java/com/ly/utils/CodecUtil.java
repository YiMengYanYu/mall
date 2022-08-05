package com.ly.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author (り夜末丶听肖邦ゞ
 * @since 2022-08-04
 */
public class CodecUtil {

    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(DigestUtils.md5Hex(data));
    }

    public static String md5Hex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }


    public static String shaHex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }


    public static String generateSalt() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "")+"YiMengYanYu";
    }

}
