package cn.huaqing.service.Impl;

import cn.huaqing.Bean.Message;
import cn.huaqing.dao.Impl.messageDaoImpl;
import cn.huaqing.dao.messageDao;
import cn.huaqing.service.messageService;

import java.util.List;
import java.util.Map;

public class messageServiceImpl implements messageService {
    private messageDao dao = new messageDaoImpl();

    @Override
    public List<Message> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public void deleteMsg(String id) {
        dao.deleteById(Integer.valueOf(id));
    }

    @Override
    public void remark(Message msg) {
        dao.updateMsg(msg);
    }

    @Override
    public Message findById(String id) {
        return dao.findByid(Integer.parseInt(id));
    }

    @Override
    public void addMessage(Message message) {
        dao.addMessage(message);
    }

    @Override
    public List<Integer> getDeletedMsg() {
        return dao.getDeletedMsg();
    }

    @Override
    public List<Map<String, Object>> getRemarkedMsg() {
        return dao.getRemarkedMsg();
    }

    @Override
    public void reback(int id) {
        dao.reback(id);
    }

    @Override
    public void updateById(int id) {
        dao.updateById(id);
    }


}
