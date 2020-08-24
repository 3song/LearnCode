/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月11日 下午5:30:46<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class OrderNumGenerator {

	// 生成订单号规则
	private static int count = 0;

	public String getNumber() {
		try {
			Thread.sleep(200);
		} catch (Exception e) {

		}
		SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return simpt.format(new Date()) + "-" + ++count;
	}

}
