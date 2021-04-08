package com.cnbg.zs.ebook.api.dto;

/**
 * @author Faye.Wang
 * @version 1.0
 * @date 2021/4/8 16:33
 * @Description
 */
public class NodeTreeDTO {
    private Integer id;
    private Integer parentId;

    private String nodeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
