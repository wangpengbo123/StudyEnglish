package cn.huaqing.service.Impl;

import cn.huaqing.Bean.testQuestions;
import cn.huaqing.dao.Impl.testDaoImpl;
import cn.huaqing.dao.testDao;
import cn.huaqing.service.testService;

import java.util.ArrayList;
import java.util.List;

public class testServiceImpl implements testService {
    private testDao dao = new testDaoImpl();

    /**
     * 查询testQuestions列表
     * @return
     */
    @Override
    public List<testQuestions> query() {
        List<testQuestions> list = dao.query();
        return list;
    }

    /**
     * 保存testQuestions对象
     * @param questions
     */
    @Override
    public void addtest(testQuestions questions) {
        dao.addtest(questions);
    }

    /**
     * 根据试题id删除对应的试题内容
     * @param id
     */
    @Override
    public void delectTest(String id) {
        dao.deleteById(Integer.parseInt(id));
    }

    /**
     * 批量删除试题
     * @param uids
     */
    @Override
    public void delSelected(String[] uids) {
        if (uids!=null&&uids.length>0){
            //1.遍历数组
            for (String id: uids) {
                //2.调用删除
                dao.deleteById(Integer.parseInt(id));
            }
        }

    }

    @Override
    public void addQuestion(testQuestions questions) {
        dao.addtest(questions);
    }

    /**
     * 查询未同步的试题
     * @return
     */
    @Override
    public List<testQuestions> queryByStatus() {
        return dao.queryByStatus();
    }

    @Override
    public void flushed(int id) {
        dao.flushed(id);
    }

    @Override
    public List<Integer> getDeletedTest() {
        return dao.getDeletedTest();
    }

    @Override
    public void updateById(Integer id) {
        dao.updateById(id);
    }


}
