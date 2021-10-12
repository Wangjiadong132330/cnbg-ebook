package com.cnbg.zs.ebook.common.file;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

public class EnviromentUtils {

    /**
     * 通过环境信息获取相应的配置
     *
     * @param key 配置key
     * @return 配置的值
     */
    public static String getValueByEnv(String key) {
        String value = getValueByEnv(key, StringUtils.EMPTY);
        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("找不到配置");
        }
        return value;
    }

    /**
     * 通过环境信息获取相应的配置
     *
     * @param key 配置key
     * @return 配置的值
     */
    public static String getValueByEnv(String key, String defaultValue) {
        Environment environment = SpringUtils.getByClass(Environment.class);
        String value = environment.getProperty(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }
}
