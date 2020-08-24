package com.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Api("SwaggerDemo控制器")
@RestController
public class SwaggerController {
    @ApiOperation("swaggerIndex首页方法")
    //@RequestMapping @RequestParam(required = true) 表示必须输入参数
    //method = {RequestMethod.POST,RequestMethod.GET} 配置 可以指定 Swagger2 显示api请求方式  默认有8个
    @RequestMapping(value = "/swaggerIndex", method = {RequestMethod.POST,RequestMethod.GET})
    public String swaggerIndex(){
        return "success";
    }
}
