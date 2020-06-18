package Connected;

import cn.huaqing.Bean.Message;
import cn.huaqing.service.Impl.messageServiceImpl;
import cn.huaqing.service.Impl.testServiceImpl;
import cn.huaqing.service.messageService;
import cn.huaqing.service.testService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/connectAppServlet")
public class connectAppServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        String json = null;
        messageService s = new messageServiceImpl();
        List<Integer> deletedMsg = s.getDeletedMsg();
        List<Map<String, Object>> remarkedMsg = s.getRemarkedMsg();
        JSONObject jsonO = new JSONObject();
        jsonO.put("deleteMsg", deletedMsg);
        jsonO.put("remarkMsg", remarkedMsg);


        //修改已评论状态为未评论   为了防止重复同步 之前已同步的数据
        for (Map<String, Object> obj : remarkedMsg) {
            Integer id = (Integer) obj.get("id");
            s.reback(id);
        }

        String json = JSONObject.toJSONString(jsonO);
        for (Integer id : deletedMsg) {
            s.updateById(id);
        }

        out.print(json);
        out.close();
    }
}
