package com.ly;

import com.ly.utils.CodecUtil;
import com.ly.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YiMeng
 * @DateTime: 2022/8/5 22:05
 * @Description: TODO
 */
@SpringBootTest
public class MallTest {

    @Test
    public void passwordTest() {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmSSSS").format(new Date()));

    }
}
