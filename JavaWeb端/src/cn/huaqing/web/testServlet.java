package cn.huaqing.web;

import cn.huaqing.Bean.testQuestions;
import cn.huaqing.dao.Impl.testDaoImpl;
import cn.huaqing.dao.testDao;
import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.Impl.testServiceImpl;
import cn.huaqing.service.messageService;
import cn.huaqing.service.testService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/testServlet")
public class testServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //调用service对象
        testService service = new testServiceImpl();
        List<testQuestions> list = service.query();
        req.setAttribute("test",list);
        req.getRequestDispatcher("/testQuestions.jsp").forward(req,resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
