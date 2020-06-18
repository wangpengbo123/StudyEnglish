package cn.huaqing.dao.Impl;

import cn.huaqing.Bean.Admin;
import cn.huaqing.dao.adminDao;
import cn.huaqing.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class adminDaoImpl implements adminDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public Admin login(String name,String password) {
        try {
            //编写SQL语句
            String sql = "select *from admin where name = ? and password = ?";
            //调用query方法
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class),
                    name,password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
