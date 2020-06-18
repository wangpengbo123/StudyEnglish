package cn.huaqing.dao.Impl;

import cn.huaqing.Bean.Message;
import cn.huaqing.dao.messageDao;
import cn.huaqing.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public class messageDaoImpl implements messageDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<Message> findAll() {
        //使用JDBC操作数据库
        //定义sql语句
        String sql = "select * from message where deleteStatus = 0";
        List<Message> messages = template.query(sql, new BeanPropertyRowMapper<Message>(Message.class));
        return messages;
    }

    @Override
    public List<Integer> getDeletedMsg() {
        String sql = "select id from message where deleteStatus = 1";
        List<Integer> messages = template.queryForList(sql,Integer.class);
        return messages;
    }

    @Override
    public List<Map<String,Object>> getRemarkedMsg() {
        String sql = "select id,remark from message where remarkStatus = 1";
        List<Map<String,Object>> messages = template.queryForList(sql);
        return messages;
    }

    @Override
    public void deleteById(int id) {
        //定义sql
        String sql = "update message set deleteStatus = 1 where id = ?";
        //执行sql语句
        template.update(sql, id);
    }

    @Override
    public void updateMsg(Message msg) {
        //定义sql
        String sql = "update message set remark = ?,remarkStatus = 1 where id = ? ";
        //执行sql语句
        template.update(sql, msg.getRemark(), msg.getId());
    }

    @Override
    public Message findByid(int id) {
        //定义sql
        String sql = "select *from message where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Message.class), id);
    }

    @Override
    public void addMessage(Message message) {
        String sql = "insert into message (id,name,content,remark) values(?,?,?,?)";
        template.update(sql, message.getId(), message.getName(), message.getContent(), message.getRemark());
    }

    @Override
    public void reback(int id) {
        String sql = "update message set remarkStatus = 1 where id = ? ";
        //执行sql语句
        template.update(sql,id);
    }

    @Override
    public void updateById(int id) {
        String sql = "update message set deleteStatus = 2 where id = ? ";
        //执行sql语句
        template.update(sql,id);
    }
}
