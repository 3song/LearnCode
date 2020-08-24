/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock.api.controller;

import com.base.BaseApiService;
import com.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月18日 下午1:10:39<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
@RequestMapping("/openApi")
public class MemberController extends BaseApiService {

	@RequestMapping("/getUser")
	public ResponseBase getUser() {
		// 1.获取对应的access_token
		// 2.使用access_token查询redis 对应value，如果value没有获取到对应的appid ，返回错误提示
		// 无效token
		// 3.如果能查询到redis，能够获取到Token信息时，使用Appid查询对应的App信息
		// 4.判断
		return setResultSuccess("获取会员信息接口");
	}
}
