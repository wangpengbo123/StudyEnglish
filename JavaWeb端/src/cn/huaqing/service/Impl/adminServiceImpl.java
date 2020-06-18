package cn.huaqing.service.Impl;

import cn.huaqing.Bean.Admin;
import cn.huaqing.dao.Impl.adminDaoImpl;
import cn.huaqing.dao.adminDao;
import cn.huaqing.service.adminService;

public class adminServiceImpl implements adminService {
    private adminDao dao = new adminDaoImpl();


    @Override
    public Admin adminlogin(Admin admin) {
        return dao.login(admin.getName(),admin.getPassword());
    }
}
