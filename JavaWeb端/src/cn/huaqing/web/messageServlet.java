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
import java.util.List;

@WebServlet("/messageServlet")
public class messageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用messageService完成查询
        messageService service = new messageServiceImpl();
        List<Message> messages = service.findAll();
        //将list存入request域
        req.setAttribute("message",messages);

        req.getRequestDispatcher("message.jsp").forward(req,resp);
        /*ArrayList<Message> list = messageDao.queryMsg();
        req.setAttribute("msg",list);
        System.out.println(1);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
