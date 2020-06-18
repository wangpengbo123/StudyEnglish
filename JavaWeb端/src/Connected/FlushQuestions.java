package Connected;

import cn.huaqing.Bean.testQuestions;
import cn.huaqing.service.Impl.testServiceImpl;
import cn.huaqing.service.testService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/flushQuestionServlet")
public class FlushQuestions extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        testService service = new testServiceImpl();
        List<testQuestions> list = service.queryByStatus();
        json.put("query",list);
        for (testQuestions questions:list) {
            int id = questions.getId();
            service.flushed(id);
        }
        List<Integer> deletedTest = service.getDeletedTest();
        json.put("deletedTest",deletedTest);
        for (Integer id:deletedTest) {
            service.updateById(id);
        }

        String jsonO= JSONObject.toJSONString(json);
        out.print(jsonO);
        out.close();
    }
}
