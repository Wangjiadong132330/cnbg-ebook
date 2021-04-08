package com.cnbg.zs.ebook.api.utils;

import com.cnbg.zs.ebook.api.entity.Permission;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2020/9/15 23:37
 * @Description
 */
public class TreeMenuUtil {
    public static Map<String,Object> mapArray = new LinkedHashMap<String, Object>();

    public  List<Permission> menuCommon;
    public  List<Object> list = new ArrayList<Object>();

    public  List<Object> treeMenu(List<Permission> menu){
        menuCommon = menu;
        for (Permission treeNode : menu) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(treeNode.getParentId()==0){
                setTreeMap(mapArr,treeNode);
                list.add(mapArr);
            }
        }
        return list;
    }

    public  List<?> menuChild(Integer id){
        List<Object> lists = new ArrayList<Object>();
        for(Permission a:menuCommon){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if(a.getParentId() .equals(id)){
                setTreeMap(childArray,a);
                lists.add(childArray);
            }
        }
        return lists;
    }

    private   void setTreeMap(Map<String,Object> mapArr,Permission treeNode){
        mapArr.put("id", treeNode.getId());
        mapArr.put("label", treeNode.getMenuName());
        mapArr.put("parentId", treeNode.getParentId());


        List<?> childrens = menuChild(treeNode.getId());
        if(childrens.size()>0){
            mapArr.put("hasChild",true);
        }
        else{
            mapArr.put("hasChildren",false);
        }
        mapArr.put("children", menuChild(treeNode.getId()));
    }
}
