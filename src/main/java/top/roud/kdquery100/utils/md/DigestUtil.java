package top.roud.kdquery100.utils.md;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/18
 * @version:
 */
public class DigestUtil {
    public static String md5(String str) {
        String md5 = "";
        try {
            md5 = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
