package cn.huaqing.service;

import cn.huaqing.Bean.testQuestions;

import java.util.ArrayList;
import java.util.List;

public interface testService {
    public List<testQuestions> query();
    public void addtest(testQuestions questions);

    public void delectTest(String id);

    public void delSelected(String[] uids);

    void addQuestion(testQuestions questions);

    public List<testQuestions> queryByStatus();

    public void flushed(int id);

    public List<Integer> getDeletedTest();

    public void updateById(Integer id);
}
