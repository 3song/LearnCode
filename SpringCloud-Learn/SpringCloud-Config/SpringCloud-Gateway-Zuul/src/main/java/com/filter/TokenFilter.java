package com.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
@Component
public class TokenFilter extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     * //表示过滤器类型 pre 表示请求之前过滤 post 请求之后过滤
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     * 表示过滤器执行顺序
     * 当一个请求在同一个阶段的时候，存在多个过滤器的时候，多个过滤器执行顺序问题
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     * 判断过滤器是否生效  默认不生效
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * @throws ZuulException if an error occurs during execution.
     * 编写过滤器拦截逻辑代码
     */
    @Override
    public Object run() throws ZuulException {
        //拦截所有的服务接口，判断服务接口上是否有传递userToken参数
        //1.获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //2.通过上下文获取request对象
        HttpServletRequest request = currentContext.getRequest();
        //3.获取token的时候  一般从请求头中获取 此处从参数中获取
        String userToken = request.getParameter("userToken");
        if(StringUtils.isEmpty(userToken)){
            //表示不会继续执行 不会调用服务接口，由网关服务直接响应客户端
            currentContext.setSendZuulResponse(false);
            //返回错误提示 和代码
            currentContext.setResponseBody("userToken is null!!!");
            currentContext.setResponseStatusCode(401);
            return null;
        }
        //不走if 就表示正常调用服务接口
        return null;
    }
    //Spring boot 过滤器如何编写
}
