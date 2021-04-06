package com.cnbg.zs.ebook.common.file;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/11/5 10:25
* @Description
*/
public class FileBean {
    private String fileOrgName;
    private String fileOrgNewName;
    private String filePath;

    public String getFileOrgName() {
        return fileOrgName;
    }
    
    public void setFileOrgName(String fileOrgName) {
        this.fileOrgName = fileOrgName;
    }
    
    public String getFileOrgNewName() {
        return fileOrgNewName;
    }
    
    public void setFileOrgNewName(String fileOrgNewName) {
        this.fileOrgNewName = fileOrgNewName;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
