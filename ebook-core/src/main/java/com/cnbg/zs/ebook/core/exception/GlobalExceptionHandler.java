package com.cnbg.zs.ebook.core.exception.handler;



import com.cnbg.zs.ebook.core.exception.AccessException;
import com.cnbg.zs.ebook.core.exception.ServiceException;
import com.cnbg.zs.ebook.core.result.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/4 23:03
* @Description
*/
@ControllerAdvice
public class GlobalExceptionHandler {
private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

/**
* 监听业务异常处理
* @param e
* @return
*/
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResultData serviceException(ServiceException e){
        logger.error("发生空指针异常！原因是：{}", e.getMessage());
        e.printStackTrace();
        return new ResultData(500,e.getMessage());
    }
    /**
    * 监听业务异常处理
    * @param e
    * @return
    */
    @ExceptionHandler(value = AccessException.class)
    @ResponseBody
    public ResultData accessException(AccessException e){
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        return new ResultData(403,e.getMessage());
    }


    /**
    * 监听业空指针常处理
    * @param e
    * @return
    */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultData nullPointerException(NullPointerException e){
        logger.error("发生空指针异常！原因是：{}", e.getMessage());
        e.printStackTrace();
        return new ResultData(500,e.getMessage());
    }

    /**
    * 处理其他异常
    * @param e
    */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultData exceptionHandler(Exception e) {
        logger.error("未知异常！原因是:", e.getMessage());
        e.printStackTrace();
        return new ResultData(500,e.getMessage());
    }

}
