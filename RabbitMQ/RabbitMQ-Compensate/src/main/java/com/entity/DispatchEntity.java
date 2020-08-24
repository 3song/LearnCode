package com.entity;

import lombok.Data;

@Data
public class DispatchEntity {

	private Long id;
	// 订单号
	private String orderId;
	// 派单路线
	private String dispatchRoute;
	// 外卖员id
	private Long takeoutUserId;
	
}
