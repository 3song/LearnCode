package com.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "imgFilter",urlPatterns = "/img/*")
//图片防盗链
public class ImgFilter implements Filter {
    @Value(value = "${domain.name}")
    private String doMainName;
    /**
     * Called by the web container to indicate to a filter that it is being
     * placed into service. The servlet container calls the init method exactly
     * once after instantiating the filter. The init method must complete
     * successfully before the filter is asked to do any filtering work.
     * <p>
     * The web container cannot place the filter into service if the init method
     * either:
     * <ul>
     * <li>Throws a ServletException</li>
     * <li>Does not return within a time period defined by the web
     *     container</li>
     * </ul>
     * The default implementation is a NO-OP.
     *
     * @param filterConfig The configuration information associated with the
     *                     filter instance being initialised
     * @throws ServletException if the initialisation fails
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following
     * pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using
     * the FilterChain object (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the
     * next entity in the filter chain to block the request processing<br>
     * 5. Directly set headers on the response after invocation of the next
     * entity in the filter chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("定义域名为"+doMainName);
        // 1.获取请求头中的来源（Referer）字段
        HttpServletRequest req = (HttpServletRequest) request;
        String referer = req.getHeader("Referer");
        if (StringUtils.isEmpty(referer)){
            System.out.println("浏览器空传");
            //转到错误地址
            request.getRequestDispatcher("/img/error.jpg").forward(request, response);
            return;
        }
        // 2.判断请求头中的域名是否和需要限制的域名一致
        String domainUrl = getDomain(referer);
        System.out.println("定义域名为"+domainUrl);
        if (!domainUrl.equals(doMainName)){
            System.out.println("链接被盗用");
            //转到错误地址
            request.getRequestDispatcher("/img/error.jpg").forward(request, response);
            return;
        }
        //放行图片
        chain.doFilter(req, response);
    }
    /*
     * @Author 陈磊
     * @Description //TODO 获取Url对应域名
     * @Date
     * @Param
     * @return
     **/
    public String getDomain(String url){
        String result="";
        int j=0,startIndex=0,endIndex=0;
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i)=='/'){
                j++;
                if (j==2){
                    startIndex=i;
                }else if (j==3){
                    endIndex=i;
                }
            }
        }
        System.out.println("startIndex+1："+startIndex+1);
        System.out.println("endIndex："+endIndex);
        result=url.substring(startIndex+1,endIndex);
        return result;
    }
    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service. This method is only called once all threads within
     * the filter's doFilter method have exited or after a timeout period has
     * passed. After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter. <br>
     * <br>
     * <p>
     * This method gives the filter an opportunity to clean up any resources
     * that are being held (for example, memory, file handles, threads) and make
     * sure that any persistent state is synchronized with the filter's current
     * state in memory.
     * <p>
     * The default implementation is a NO-OP.
     */
    @Override
    public void destroy() {

    }
}
