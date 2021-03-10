package com.jontyhua.web.util;

import java.util.UUID;

public class RandomTool {
    /**
     * 生成32位随机字符串
     * @return
     */
    public static String randomCode() {
        return UUID.randomUUID().toString().replace("-","");
    }
}