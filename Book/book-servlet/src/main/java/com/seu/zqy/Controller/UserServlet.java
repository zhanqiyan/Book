package com.seu.zqy.Controller;

import com.mysql.cj.Session;
import com.seu.zqy.bean.User;
import com.seu.zqy.service.UserserviceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        UserserviceImpl userservice = new UserserviceImpl();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginuser = userservice.login(new User(null, username, password, null));
        if (loginuser == null) {
            System.out.println("用户名或者密码错误");
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登录成功");

            //保存用户登录之后的信息到session域中
            req.getSession().setAttribute("user",loginuser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、 获取请求的参数
        // 2、 检查 验证码是否正确 === 写死,要求验证码为:abcde
        // 3、 检查 用户名是否可用
        // 4、可用，跳转成功页面，失败则跳回注册页面
        UserserviceImpl userservice = new UserserviceImpl();
        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if (token!=null&&token.equalsIgnoreCase(code)) {
            if (userservice.existsUsername(username)) {
                System.out.println("用户名已存在");
                req.setAttribute("msg", "用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                User user = new User(1, username, password, email);
                userservice.registUser(user);
                System.out.println("注册保存成功！");
                //为了避免表单重复提交之按F5，建议保存数据库操作后直接重定向，而不是请求转发
//                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                resp.sendRedirect("index.jsp");
            }

        } else {
            System.out.println("验证码错误");
            req.setAttribute("msg", "验证码输入有误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
