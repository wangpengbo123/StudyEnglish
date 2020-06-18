package cn.huaqing.dao.Impl;

import cn.huaqing.Bean.testQuestions;
import cn.huaqing.dao.testDao;
import cn.huaqing.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class testDaoImpl implements testDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<testQuestions> query() {
        String sql = "select *from questions where deletedStatus = 0";
        List<testQuestions> list = template.query(sql, new BeanPropertyRowMapper<>(testQuestions.class));
        return list;
    }

    @Override
    public void addtest(testQuestions questions) {
        //1.定义sql语句
        String sql = "insert into questions (question,answer,optionA,optionB,optionC,optionD) values (?,?,?,?,?,?)";
        //执行sql
        template.update(sql,questions.getQuestion(),questions.getAnswer(),
                questions.getOptionA(),questions.getOptionB(),questions.getOptionC(),questions.getOptionD());
    }

    @Override
    public void deleteById(int id) {
        //1.定义sql语句
        String sql = "update questions set deletedStatus = 1 where id = ?";
        template.update(sql,id);
    }

    @Override
    public List<testQuestions> queryByStatus() {
        String sql = "select * from questions where flushedStatus = 0";
        List<testQuestions> list = template.query(sql, new BeanPropertyRowMapper<>(testQuestions.class));
        return list;
    }

    @Override
    public void flushed(int id) {
        String sql = "update questions set flushedStatus = 1 where id = ?";
        template.update(sql,id);
    }
    public List<Integer> getDeletedTest() {
        String sql = "select id from questions where deletedStatus = 1";
        List<Integer> list = template.queryForList(sql, Integer.class);
        return list;
    }

    @Override
    public void updateById(int id) {
        String sql = "update questions set deletedStatus = 2 where id = ?";
        template.update(sql,id);
    }
}
