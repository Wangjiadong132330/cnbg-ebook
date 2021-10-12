package com.cnbg.zs.ebook.common.file;

/**
 * 从容器中获取bean工具类
 */
public class SpringUtils {
    private SpringUtils() {
    }

    /**
     * 通过beanid获取bean
     *
     * @param beanId beanid
     * @param <T>    泛型
     * @return 实例
     */
    public static <T> T get(String beanId) {
        if (SpringContext.getApplicationContext() == null) {
            return null;
        }
        return (T)SpringContext.getApplicationContext().getBean(beanId);
    }

    /**
     * 通过class获取bean
     *
     * @param className 类型
     * @param <T>       泛型
     * @return 实例
     */
    public static <T> T getByClass(Class<T> className) {
        if (SpringContext.getApplicationContext() == null) {
            return null;
        }
        return (T)SpringContext.getApplicationContext().getBean(className);
    }
}
