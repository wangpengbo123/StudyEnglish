package cn.huaqing.web;

import cn.huaqing.Bean.Message;
import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.messageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findMsgServlet")
public class findMsgServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        String id = req.getParameter("id");
        //调用service查询
        messageService service = new messageServiceImpl();
        Message msg = service.findById(id);
        //将msg存入到request
        req.setAttribute("msg",msg);
        //转发到remark.jsp
        req.getRequestDispatcher("/remark.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
