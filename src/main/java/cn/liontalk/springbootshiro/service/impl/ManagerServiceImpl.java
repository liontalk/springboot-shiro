package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.ManagerDao;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public List<ManagerEntity> queryAllManager() {
        return managerDao.queryAllManager();
    }

    @Override
    public void insertManager(ManagerEntity managerEntity) {
        managerDao.insertManager(managerEntity);
    }

    @Override
    public int  deleteManagerInfo(List<Integer> list) {
       return  managerDao.deleteManager(list);
    }

    @Override
    public void updateManager(ManagerEntity managerEntity) {
        managerDao.updateManager(managerEntity);
    }
}
