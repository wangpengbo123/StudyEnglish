package cn.huaqing.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Druid连接池
 */
public class JDBCUtil {
    private static DataSource ds;
    static{

        try {
            //1.加载配置文件
            Properties properties = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream resource = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(resource);
            //2.初始化连接池对象
             ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接connection对象
         */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
