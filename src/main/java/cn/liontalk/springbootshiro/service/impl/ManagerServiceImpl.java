package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.constant.SysConstant;
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
    public int insertManager(ManagerEntity managerEntity) {
        return managerDao.insertManager(managerEntity);
    }

    @Override
    public int deleteManagerInfo(List<Integer> list) {
        return managerDao.deleteManager(list);
    }

    @Override
    public void updateManager(ManagerEntity managerEntity) {
        managerDao.updateManager(managerEntity);
    }

    @Override
    public ManagerEntity queryManagerById(int id) {
        return managerDao.queryManagerById(id);
    }

    @Override
    public void updatePassword(int userId, String password) {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setPassword(password);
        managerEntity.setUserId(userId);
        managerEntity.setStatus(SysConstant.NORMAL_STATUS);
        managerDao.updateManager(managerEntity);
    }

}
