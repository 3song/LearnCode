/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.mapper;

import com.entity.AppEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月18日 下午3:15:38<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public interface AppMapper {

	@Select("SELECT ID AS ID ,APP_NAME AS appName, app_id as appId, app_secret as appSecret ,is_flag as isFlag , access_token as accessToken from m_app "
			+ "where app_id=#{appId} and app_secret=#{appSecret}  ")
    AppEntity findApp(AppEntity appEntity);

	@Select("SELECT ID AS ID ,APP_NAME AS appName, app_id as appId, app_secret as appSecret ,is_flag as isFlag  access_token as accessToken from m_app "
			+ "where app_id=#{appId} and app_secret=#{appSecret}  ")
    AppEntity findAppId(@Param("appId") String appId);

	@Update(" update m_app set access_token =#{accessToken} where app_id=#{appId} ")
	int updateAccessToken(@Param("accessToken") String accessToken, @Param("appId") String appId);
}
