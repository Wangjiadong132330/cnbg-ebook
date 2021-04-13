package com.cnbg.zs.ebook.api.dto;

public class ImportResultDTO {

    // 导入总数
    private int importCount;

    // 存在总数
    private int existCount;

    public int getImportCount() {
        return importCount;
    }

    public void setImportCount(int importCount) {
        this.importCount = importCount;
    }

    public int getExistCount() {
        return existCount;
    }

    public void setExistCount(int existCount) {
        this.existCount = existCount;
    }
}
