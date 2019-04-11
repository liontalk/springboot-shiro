package cn.liontalk.springbootshiro.service.impl;

import cn.liontalk.springbootshiro.dao.ManagerDao;
import cn.liontalk.springbootshiro.entity.ManagerEntity;
import cn.liontalk.springbootshiro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    ManagerDao managerDao;


    @Override
    public ManagerEntity findManagerByName(String name) {
        return managerDao.findManagerByName(name);
    }
}
