package cn.huaqing.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Request域中共享的admin对象
        Object account = req.getAttribute("account");
        req.setAttribute("account",account);
        if (account != null) { //防止空指针异常
            //给页面写一句话
            resp.setContentType("text/html;charset=utf-8");
//            resp.getWriter().write("登陆成功! 管理员 "+account+",欢迎您！");
            Cookie[] cookies = req.getCookies();
            boolean flag = false;
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("LastLogin".equals(name)) {
                        flag = true;
                        //获取cookie
                        String value = cookie.getValue();
                        String decode = URLDecoder.decode(value, "utf-8");
                        req.setAttribute("decode", decode);
                        System.out.println(decode);
                        //重新设置cookie
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                        String fd = sdf.format(date);
                        String encode = URLEncoder.encode(fd, "utf-8");
                        cookie.setValue(encode);
                        //设置cookie存活时间
                        cookie.setMaxAge(60*60*24*30);
                        //添加cookie
                        resp.addCookie(cookie);

                    }
                }
            }
            if (cookies==null||cookies.length==0||flag==false){
                flag=true;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                String format = sdf.format(date);
                String encode = URLEncoder.encode(format, "utf-8");
                Cookie cookie = new Cookie("LastLogin",encode);
                cookie.setMaxAge(60*60*24*30);
                resp.addCookie(cookie);
                req.setAttribute("decode",account+"您好，欢迎您首次访问");
//                out.write("<h3>"+account+"您好，欢迎您首次访问</h3>");
//                resp.getWriter().write("<h3>"+account+"您好，欢迎您首次访问</h3>");
            }
            req.setAttribute("flag",flag);
            req.getRequestDispatcher("/main.jsp").forward(req,resp);
        }
    }
}
