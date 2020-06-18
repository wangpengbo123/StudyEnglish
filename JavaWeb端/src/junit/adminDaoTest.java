package junit;


import cn.huaqing.Bean.Message;
import cn.huaqing.dao.Impl.adminDaoImpl;
import cn.huaqing.dao.Impl.messageDaoImpl;
import cn.huaqing.dao.messageDao;
import cn.huaqing.Bean.Admin;
import cn.huaqing.dao.adminDao;
import org.junit.Test;

import java.util.List;

public class adminDaoTest {

@Test
    public void testLogin(){

    Admin admin = new Admin();
    admin.setName("manger");
    admin.setPassword("123456");
    adminDao dao = new adminDaoImpl();
    Admin login = dao.login(admin.getName(),admin.getPassword());
    System.out.println(login);
    }
    @Test
    public void testquery(){
        messageDao msg = new messageDaoImpl();
        List<Message> query = msg.findAll();
        for (Message ms: query) {
            System.out.println(ms);
        }
    }
    @Test
    public void msgUpdate(){
        messageDao messageDao = new messageDaoImpl();
        Message message=new Message(2,"李四","测试","success");
        messageDao.updateMsg(message);
        List<Message> all = messageDao.findAll();
        for (Message ms: all) {
            System.out.println(ms);
        }
    }
}
