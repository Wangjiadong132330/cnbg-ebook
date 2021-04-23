package com.cnbg.zs.ebook.core.controller;
import com.cnbg.zs.ebook.core.result.ResultData;
import com.cnbg.zs.ebook.core.result.ResultEnum;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/12/19 10:27
* @Description
*/
public class BaseController<T> {

	public ResultData resultSuccess(){
		return  new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage());
	}

	public ResultData resultSuccess(T data){
		return  new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),ResultEnum.HTTP_SUCCESS.getMessage(),data);
	}

	public ResultData resultSuccess(String msg,T data){
	return  new ResultData<>(ResultEnum.HTTP_SUCCESS.getCode(),msg,data);
	}

	public ResultData resultFail(){
		return  new ResultData<>(ResultEnum.HTTP_ERROR_500.getCode(),ResultEnum.HTTP_ERROR_500.getMessage());
	}

	public ResultData resultFail(T data){
		return  new ResultData<>(ResultEnum.HTTP_ERROR_500.getCode(),ResultEnum.HTTP_ERROR_500.getMessage(),data);
	}
	public ResultData resultFail(String msg,T data){
		return  new ResultData<>(ResultEnum.HTTP_ERROR_500.getCode(),msg,data);
	}

	public ResultData resultFail(int code,String msg,T data){
		return  new ResultData<>(code,msg,data);
	}

}
