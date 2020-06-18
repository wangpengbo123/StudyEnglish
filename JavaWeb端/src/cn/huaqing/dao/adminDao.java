package cn.huaqing.dao;

import cn.huaqing.utils.JDBCUtil;
import cn.huaqing.Bean.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * admin类的Dao层
 */
public interface adminDao {


    /**
     *
     * @param name
     * @param password
     * @return
     */
    public Admin login(String name,String password);

}
