package com.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

// 服务接口有响应  统一规范响应服务接口信息
@Data
@Slf4j
public class ResponseBase {

	private Integer rtnCode;
	private String msg;
	private Object data;

	public ResponseBase() {

	}

	public ResponseBase(Integer rtnCode, String msg, Object data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}

	public static void main(String[] args) {
		ResponseBase responseBase = new ResponseBase();
		responseBase.setData("123456");
		responseBase.setMsg("success");
		responseBase.setRtnCode(200);
		System.out.println(responseBase.toString());
		log.info("itmayiedu...");
	}

	@Override
	public String toString() {
		return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
	}

}
