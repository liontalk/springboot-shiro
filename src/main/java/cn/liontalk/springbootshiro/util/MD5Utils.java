package cn.liontalk.springbootshiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 周哲
 * @package: cn.liontalk.springbootshiro.util
 * @description: MD5加密工具
 * @date: 2019/4/14 11:45
 * @version: V1.0
 */
public class MD5Utils {


    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    private static final String SALT= "SpringBootShiro";

    private static final String ENCRYPT_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new SimpleHash(ENCRYPT_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String password) {
        String newPassword = new SimpleHash(ENCRYPT_NAME, password, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }


    public static void main(String[] args) {
        System.out.println(encrypt("admin","123456"));
    }


}
