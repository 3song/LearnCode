/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.antmeite.api.order.impl;

import com.antmeite.api.order.IOrderService;
import com.antmeite.code.base.BaseApiService;
import com.antmeite.code.base.ResponseBase;
import com.antmeite.feign.StockFeign;
import com.antmeite.mapper.OrderMapper;
import com.codingapi.tx.annotation.TxTransaction;
import com.itmayeidu.api.entity.OrderEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

//import com.codingapi.tx.annotation.TxTransaction;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月24日 下午2:58:07<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class IOrderServiceImpl extends BaseApiService implements IOrderService {
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private StockFeign stockFeign;

	// 下单扣库存
	@TxTransaction(isStart = true)
	@Transactional
	@GetMapping(value = "/addOrderAndStock")
	public ResponseBase addOrderAndStock(int i) throws Exception {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setName("购买Switch订单");
		orderEntity.setOrderCreatetime(new Date());
		// 价格是300元
		orderEntity.setOrderMoney(300d);
		// 状态为 未支付
		orderEntity.setOrderState(0);
		Long commodityId = 30l;
		// 商品id
		orderEntity.setCommodityId(commodityId);
		//---------封装订单状态----------
		// 1.先下单，创建订单 向订单数据库中插入一条数据
		int orderResult = orderMapper.addOrder(orderEntity);
		System.out.println("orderResult:" + orderResult);
		// 2.下单成功后,调用库存服务 对该商品数量减去1 采用feign方式调用库存服务接口
		ResponseBase inventoryReduction = stockFeign.inventoryReduction(commodityId);
		if (inventoryReduction.getRtnCode() != 200) {
			// 1.使用手动事务 -
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// 2.获取将异常抛出给上一层，外面回滚。
			throw new Exception("调用库存服务接口失败，开始回退订单事务代码");
		}
		int result = 1 / i;
		System.out.println("result:"+result);
		return setResultSuccess("下单成功!");
	}

}
