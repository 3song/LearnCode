package com.httprequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final HttpServletRequest request;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XSSHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

    @Override
    public String getParameter(String name) {
        //获取之前的参数
        String value=super.getParameter(name);
        System.out.println("原来的参数："+value);
        if (StringUtils.isNotEmpty(value)){
            //将参数中的特殊字符进行转换
            value = StringEscapeUtils.escapeHtml4(value);
            System.out.println("转换后的参数："+value);
        }
        return value;
    }
}
