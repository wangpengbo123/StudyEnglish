package cn.huaqing.dao;

import cn.huaqing.Bean.Message;

import java.util.List;
import java.util.Map;

/*
* 用户留言的Dao
* */
public interface messageDao {

    public List<Message> findAll();

    public List<Integer> getDeletedMsg();

    public List<Map<String,Object>> getRemarkedMsg();

    public void deleteById(int id);

    public void updateMsg(Message msg);

    public Message findByid(int id);

    public void addMessage(Message message);

    public void reback(int id);

    void updateById(int id);


   /* public static ArrayList<Message> queryMsg(){
        ArrayList<Message> list = null;

        //获取连接对象
        try {
            Connection conn = JDBCUtil.getConnection();
            //定义sql语句
            String sql = "select *from messages";
            //获取执行sql的对象
            Statement statement = conn.createStatement();
            //执行sql
            ResultSet rs = statement.executeQuery(sql);
            Message msg ;
            list = new ArrayList<>();
            while(rs.next()){

                int id = rs.getInt(1);
                String name = rs.getString(2);
                String content = rs.getString(3);
                String remark = rs.getString(4);
                msg = new Message(id,name,content,remark);
                list.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/
     /*static int delete(int id){
        int i =0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "delect *from Messages where id="+id;
            Statement statement = conn.createStatement();
            i = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }*/
}
