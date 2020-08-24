package com.antmeite.api.stock.impl;

import com.antmeite.api.entity.StockEntity;
import com.antmeite.api.stock.StockService;
import com.antmeite.code.base.BaseApiService;
import com.antmeite.code.base.ResponseBase;
import com.antmeite.mapper.StockMapper;
import com.codingapi.tx.annotation.TxTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockServiceImpl extends BaseApiService implements StockService {
	@Resource
	private StockMapper stockMapper;

	@TxTransaction()
	@Transactional
	@RequestMapping("/inventoryReduction")
	public ResponseBase inventoryReduction(@RequestParam("commodityId") Long commodityId) {
		if (commodityId == null) {
			return setResultError("商品id不能为空!");
		}
		// 1.查询该商品id 是否存在
		StockEntity stockEntity = stockMapper.selectStock(commodityId);
		if (stockEntity == null) {
			return setResultError("商品id不存在!");
		}
		// 2.判断商品是否有超卖
		if (stockEntity.getStock() <= 0) {
			return setResultError("当前商品已经买完啦!");
		}
		// 3.减去库存1
		int updateStockResult = stockMapper.updateStock(commodityId);
		if (updateStockResult <= 0) {
			return setResultError("修改库存失败!");
		}
		return setResultSuccess("修改库存成功!");
	}

}
