package cn.huaqing.web;

import cn.huaqing.Bean.Admin;
import cn.huaqing.service.Impl.adminServiceImpl;
import cn.huaqing.service.adminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class adminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //封装admin对象
        Admin loginadmin = new Admin();
        loginadmin.setName(username);
        loginadmin.setPassword(password);
        //调用adminService的login方法
        adminService service = new adminServiceImpl();
        Admin admin = service.adminlogin(loginadmin);

        //判断admin
        if (admin==null){
            //登陆失败
            req.getRequestDispatcher("/Fail.jsp").forward(req,resp);
        }else{
            //登陆成功
            HttpSession session = req.getSession();
            session.setAttribute("Login",admin);
            req.setAttribute("account",username);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }
}
