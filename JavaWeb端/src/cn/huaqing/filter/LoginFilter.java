/*
package cn.huaqing.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取资源请求路径
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        //判断是否包含相关资源路径
        if (requestURI.contains("/index.jsp") || requestURI.contains("/loginServlet")){
            //包含，则登录
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //
            Object account = ((HttpServletRequest) servletRequest).getSession().getAttribute("Login");
            if (account != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                servletRequest.getRequestDispatcher("/index.jsp").forward(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
*/
