package cn.huaqing.web;

import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.messageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteMsgServlet")
public class deleteMsgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        String id = req.getParameter("id");
        System.out.println(id);
        //调用Service删除
        messageService service = new messageServiceImpl();
        service.deleteMsg(id);

        //重定向
        resp.sendRedirect(req.getContextPath()+"/messageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
