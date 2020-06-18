package cn.huaqing.web;

import cn.huaqing.Bean.Message;
import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.messageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/remarkServlet")
public class remarkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        //2.获取内容
        Map<String, String[]> map = req.getParameterMap();
        //3.封装对象
        Message msg = new Message();
        try {
            BeanUtils.populate(msg,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用service方法修改
        messageService service = new messageServiceImpl();
        service.remark(msg);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/messageServlet");

        //向客户端发送数据
        /*resp.setContentType("text/html; charset=UTF-8" );
        PrintWriter out = resp.getWriter();
        String json = null;
//        messageService s =new messageServiceImpl();
        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(msg);
        System.out.println(json);
        out.print(json);
        *//*List<Message> list=s.findAll();
        for(Message m:list){
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(msg);
            System.out.println(json);
            out.print(json);
        }*//*

        out.flush();
        out.close();*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
