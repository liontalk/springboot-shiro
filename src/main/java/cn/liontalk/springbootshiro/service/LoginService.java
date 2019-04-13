package cn.liontalk.springbootshiro.service;

import cn.liontalk.springbootshiro.entity.ManagerEntity;

/**
 *
 */
public interface LoginService {


    /**
     * 根据名称查找管理员
     *
     * @param name 管理员名称
     * @return ManagerEntity 查找到的管理员信息
     */
    ManagerEntity findManagerByName(String name);
}
