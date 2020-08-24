/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseApiService;
import com.base.ResponseBase;
import com.entity.AppEntity;
import com.mapper.AppMapper;
import com.utils.BaseRedisService;
import com.utils.TokenUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// 创建获取getAccessToken
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseApiService {
	@Resource
	private BaseRedisService baseRedisService;
	private long timeToken = 60 * 60 * 2;
	@Resource
	private AppMapper appMapper;

	// 使用appId+appSecret 生成AccessToke
	@RequestMapping("/getAccessToken")
	public ResponseBase getAccessToken(AppEntity appEntity) {
		//获取生成的AppId和AppSecret 并验证是否可用
		AppEntity appResult = appMapper.findApp(appEntity);
		if (appResult == null) {
			return setResultError("没有对应机构的认证信息");
		}
		int isFlag = appResult.getIsFlag();
		//为1 为不可用
		if (isFlag == 1) {
			return setResultError("您现在没有权限生成对应的AccessToken");
		}
		// ### 获取新的accessToken 之前删除之前老的accessToken 不能缓存AppId对象
		String accessToken = appResult.getAccessToken();
		// 从redis中删除之前的accessToken
		// redis key accessToken 22c42436e0ef4426b1f7eb5ed21cfe46 value appResult.getAppId() 73b5d54c09451531897238555
		baseRedisService.delKey(accessToken);
		// 生成的新的accessToken
		String newAccessToken = newAccessToken(appResult.getAppId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accessToken", newAccessToken);
		return setResultSuccessData(jsonObject);
	}

	private String newAccessToken(String appId) {
		// 使用AppId和AppSecret 生成对应的AccessToken 保存两个小时
		String accessToken = TokenUtils.getAccessToken();
		// 保证在同一个事物redis 事物中
		// 生成最新的token key为accessToken value 为 appId
		baseRedisService.setString(accessToken, appId, timeToken);
		// 表中保存当前accessToken
		appMapper.updateAccessToken(accessToken, appId);
		return accessToken;
	}
}
