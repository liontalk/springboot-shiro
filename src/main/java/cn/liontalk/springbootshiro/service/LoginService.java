package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.ManagerEntity;

public interface LoginService {


    /**
     * 根据名称查找管理员
     */
    ManagerEntity findManagerByName(String name);
}
