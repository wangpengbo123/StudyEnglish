package cn.huaqing.web;

import cn.huaqing.service.Impl.testServiceImpl;
import cn.huaqing.service.testService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class deleteSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取所有的id
        String[] uids = req.getParameterValues("uid");
        //2.调用service方法
        testService service = new testServiceImpl();
        service.delSelected(uids);
        //3.跳转查询界面
        resp.sendRedirect(req.getContextPath()+"/testServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
