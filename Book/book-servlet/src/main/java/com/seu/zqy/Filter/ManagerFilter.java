package com.seu.zqy.Filter;

import com.seu.zqy.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        //用户未登录，让他喵的登录
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        } else {
            //不是管理员，进行拦截
            if (!user.getUsername().equalsIgnoreCase("admin")) {
                httpServletRequest.getRequestDispatcher("/pages/user/not_manager.jsp").forward(servletRequest, servletResponse);
            } else {
                //是管理员权限，就放行
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
