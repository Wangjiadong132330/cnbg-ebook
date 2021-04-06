package com.cnbg.zs.ebook.common.lang;


import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/10/26 13:32
* @Description
*/
public class DtoVoUtils {
    public static <T> T convert(Object sourceClass,Class<T> targetClass){
    if(StringUtils.isEmpty(sourceClass)){
        return null;
    }
    if(StringUtils.isEmpty(targetClass)){
        return null;
    }
    try {
        T instance = targetClass.getDeclaredConstructor().newInstance();

        BeanUtils.copyProperties(sourceClass,instance);
        return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

 /**
    * pageInfo DTO ENTITY VO CONVERT METHOD
    * @param pageInfo
    * @param targetClass
    * @param <T>
    * @param <V>
    * @return

    public static <T,V>PageInfo<V> pageDtoConvert(PageInfo<T> pageInfo,Class<V> targetClass){
        List<T> sourceList = pageInfo.getList();
        Page<V> vPage = new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize());
        vPage.setTotal(pageInfo.getTotal());
        sourceList.forEach(item->{
            try {
                V target = (V) convert(item,targetClass.getDeclaredConstructor().newInstance().getClass());
                vPage.add(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new PageInfo<>(vPage);
    }
*/
    /**
    * DTO ENTITY VO CONVERT METHOD
    * @param sourceList
    * @param targetClass
    * @param <T>
    * @param <V>
    * @return
    */
    public static <T,V> List<V> lisDtoConvert(List<T> sourceList,Class<V> targetClass){
        if(CollectionUtils.isEmpty(sourceList)){
            return null;
        }else {
            List<V> targetList = new ArrayList<>();
        sourceList.forEach(item->{
            try {
                V target = (V)convert(item,targetClass.getDeclaredConstructor().newInstance().getClass());
                targetList.add(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
            return targetList;
        }
    }

}
