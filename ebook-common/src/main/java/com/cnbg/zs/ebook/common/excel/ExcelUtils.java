package com.cnbg.zs.ebook.common.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/10/30 16:57
* @Description
*/
public class ExcelUtils<T> {
    /**
    * 读取excel
    * @param excel
    * @param clazz
    * @param sheetNo
    * @param rowNumber
    * @param <T>
    * @return
    */
    public static <T> ArrayList<T> readExcel(MultipartFile excel, Class<T> clazz,Integer sheetNo,Integer rowNumber){
        GeneralExcelListener<T> listener = null;
        try {
            InputStream in = new BufferedInputStream(excel.getInputStream());
            listener = new GeneralExcelListener<>();
            EasyExcelFactory.read(in,clazz,listener).sheet(sheetNo).headRowNumber(rowNumber).doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return listener.getSuccessDatas();
     }

    /**
    * 导出
    * @param response
    * @param list
    * @param fileName
    * @param sheetName
    * @param clazz
    */
    public static void writeExcel(HttpServletResponse response, List<?> list,String fileName,String sheetName, Class clazz){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = new String( fileName.getBytes("UTF-8"), "ISO8859-1" );
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(),clazz).sheet(0,sheetName).doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * 导出
    * @param response
    * @param list
    * @param fileName
    * @param sheetName
    * @param headList
    */
    public static void writeExcel(HttpServletResponse response, List<?> list,String fileName,String sheetName, List<List<String>> headList ){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = new String( fileName.getBytes("UTF-8"), "ISO8859-1" );
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream()).registerWriteHandler(new CustomCellWriteHandler()).sheet(0,sheetName).head(headList).doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
