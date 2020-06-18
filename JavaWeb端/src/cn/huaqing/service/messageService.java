package cn.huaqing.service;


import cn.huaqing.Bean.Message;

import java.util.List;
import java.util.Map;

/**
 * 管理员的业务接口
 */
public interface messageService {
    /**
     * 查询所有用户留言信息
     *
     * @return
     */
    public List<Message> findAll();

    public void deleteMsg(String id);

    public void remark(Message msg);

    public Message findById(String id);

    public void addMessage(Message message);

    public List<Integer> getDeletedMsg();

    public List<Map<String, Object>> getRemarkedMsg();

    public void reback(int id);

    void updateById(int id);
}
