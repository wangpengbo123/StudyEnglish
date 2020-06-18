package cn.huaqing.web;

import cn.huaqing.Bean.testQuestions;
import cn.huaqing.service.Impl.testServiceImpl;
import cn.huaqing.service.testService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addtestServlet")
public class addtestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取参数
        Map<String, String[]> map = req.getParameterMap();

        /*int id = Integer.parseInt(req.getParameter("id"));
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");
        String optionA = req.getParameter("optionA");
        String optionB = req.getParameter("optionB");
        String optionC = req.getParameter("optionC");
        String optionD = req.getParameter("optionD");*/
        //封装对象
        testQuestions questions = new testQuestions();
        try {
            BeanUtils.populate(questions,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service保存
        testService service = new testServiceImpl();
        service.addtest(questions);
        //跳转
        resp.sendRedirect(req.getContextPath()+"/testServlet");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
