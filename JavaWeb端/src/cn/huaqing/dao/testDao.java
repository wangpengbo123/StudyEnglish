package cn.huaqing.dao;

import cn.huaqing.utils.JDBCUtil;
import cn.huaqing.Bean.testQuestions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface testDao {

    public List<testQuestions> query();

    public void addtest(testQuestions questions);

    public void deleteById(int id);

    public List<testQuestions> queryByStatus();

    public void flushed(int id);

    public List<Integer> getDeletedTest();

    public void updateById(int id);

    /*{
        ArrayList<testQuestions> list = null;
        try{
            //获取连接对象
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            //sql语句
            String sql = "select *from questions";
            ResultSet rs = statement.executeQuery(sql);
            testQuestions questions1;

            if (rs.next()){
                list = new ArrayList<>();

                int id = rs.getInt(1);
                String question = rs.getString(2);
                String answer = rs.getString(3);
                String optionA = rs.getString(4);
                String optionB  = rs.getString(5);
                String optionC = rs.getString(6);
                String optionD = rs.getString(7);
                questions1 = new testQuestions(id,question,answer,optionA,optionB,optionC,optionD);
                list.add(questions1);
            }
        *//*for (testQuestions testquestions: list) {
            System.out.println(testquestions.toString());
        }*//*

        }catch  (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }*/

   /* public static void main(String[] args) throws SQLException {
        testDao.query();
    }*/
}
