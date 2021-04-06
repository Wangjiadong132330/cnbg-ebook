package com.cnbg.zs.ebook.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

    /**
    * @author Faye.Wang
    * @version 1.0
    * @date 2020/10/30 17:06
    * @Description
    */
    public class GeneralExcelListener <T> extends AnalysisEventListener<T> {
        private final static Logger LOGGER = LoggerFactory.getLogger(GeneralExcelListener.class);
        //自定义用于暂时存储data。
        //可以通过实例获取该值
        private ArrayList<T> successDatas = new ArrayList<>();
        private ArrayList<T> errorDatas = new ArrayList<>();

        @Override
        public void invoke(T t, AnalysisContext analysisContext) {
        successDatas.add(t);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }

        @Override
        public void onException(Exception exception, AnalysisContext context) {
            LOGGER.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
            if (exception instanceof ExcelDataConvertException) {
                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
                LOGGER.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
            }
        }

    public ArrayList<T> getErrorDatas() {
    return errorDatas;
    }

    public ArrayList<T> getSuccessDatas() {
    return successDatas;
    }
}
