package cn.huaqing.web;

import cn.huaqing.Bean.Message;
import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.messageService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/submitServlet")
public class SubmitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("connect success");
        String s = IOUtils.toString(req.getInputStream(), "utf8");
        if (!StringUtils.isEmpty(s)) {
            JSONObject json = JSONObject.parseObject(s);
            System.out.println(json);
            messageService service = new messageServiceImpl();
            Integer id = json.getInteger("id");
            String name = json.getString("name") == null ? "" : json.getString("name");
            String content = json.getString("content") == null ? "" : json.getString("content");
            String remark = json.getString("remark") == null ? "" : json.getString("remark");
            Message message = new Message(id, name, content, remark);
            System.out.println(message);
            service.addMessage(message);
            System.out.println("留言已同步到服务器端");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
