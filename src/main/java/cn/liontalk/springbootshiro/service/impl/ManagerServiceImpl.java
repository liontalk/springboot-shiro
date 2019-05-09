package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.ManagerDao;
import cn.liontalk.springbootshiro.dao.RoleDao;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.entity.RoleEntity;
import cn.liontalk.springbootshiro.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<ManagerEntity> queryAllManager() {
        return managerDao.queryAllManager();
    }

    @Override
    public int insertManager(ManagerEntity managerEntity) {

        int result = managerDao.insertManager(managerEntity);
        if (result > 0 && !CollectionUtils.isEmpty(managerEntity.getRoleEntityList())) {
            roleDao.insertManagerAndRole(managerEntity.getUserId(),managerEntity.getRoleEntityList());
        }
        return result;
    }

    @Override
    public int deleteManagerInfo(List<Integer> list) {
        return managerDao.deleteManager(list);
    }

    @Override
    public void updateManagerInfo(ManagerEntity managerEntity) {
        managerDao.updateManager(managerEntity);
    }

    @Override
    public ManagerEntity queryManagerById(int id) {
        List<Long> roleEntityList = new ArrayList<>();
        ManagerEntity managerEntity = managerDao.queryManagerById(id);
        if (null != managerEntity) {
            List<RoleEntity> list = roleDao.queryManagerRoleById(id);
            if (!CollectionUtils.isEmpty(list)) {
                for (RoleEntity entity : list) {
                    roleEntityList.add(entity.getRoleId());
                }
            }
            managerEntity.setRoleEntityList(roleEntityList);
        }
        return managerEntity;
    }

    @Override
    public void updatePassword(int userId, String password) {
        ManagerEntity managerEntity = new ManagerEntity();
        managerEntity.setPassword(password);
        managerEntity.setUserId(userId);
        managerEntity.setStatus(null);
        managerDao.updateManager(managerEntity);
    }

}
